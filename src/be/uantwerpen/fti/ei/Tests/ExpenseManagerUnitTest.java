package be.uantwerpen.fti.ei.Tests;

import be.uantwerpen.fti.ei.DB.*;
import be.uantwerpen.fti.ei.Expense.*;
import be.uantwerpen.fti.ei.ExpenseManager.*;
import be.uantwerpen.fti.ei.Split.*;
import be.uantwerpen.fti.ei.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseManagerUnitTest {

    private ExpenseManager expenseManager;
    private UserDB userDB;
    private ExpenseDB expenseDB;
    private User user1, user2;
    private Expense expense;

    @BeforeEach
    void setUp() {
        // Resetting singleton instances before each test might be necessary to ensure test isolation
        // UserDB.resetInstance();
        // ExpenseDB.resetInstance();

        userDB = UserDB.getInstance();
        expenseDB = ExpenseDB.getInstance();
        expenseManager = new ExpenseManager(userDB, expenseDB);

        // Setup test users
        user1 = new User("John");
        user2 = new User("Jeff");
    }

    @Test
    void testAddUser() {
        expenseManager.addUser(user1);
        assertTrue(userDB.getData().contains(user1));
    }

    @Test
    void testRemoveUser() {
        expenseManager.addUser(user1);
        expenseManager.removeUser(user1);
        assertFalse(userDB.getData().contains(user1));
    }

    @Test
    void testAddExpense() throws Exception {
        List<Split> splits = Arrays.asList(
                new ExactSplit(user1, 50), // user1's share
                new ExactSplit(user2, 50)  // user2's share
        );
        expenseManager.addExpense("Dinner", "EXACT", 100, user1, splits);
        Expense addedExpense = expenseDB.getData().get(0); // Assuming this is the first and only expense
        assertEquals(100, addedExpense.getAmount());
        assertEquals("Dinner", addedExpense.getName());
    }

    @Test
    void testSettleDebt() {
        // Add users
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);

        // Create an expense where user1 paid $100, and it's supposed to be split between user1 and user2
        List<Split> splits = Arrays.asList(
                new ExactSplit(user1, 60), // user1's share
                new ExactSplit(user2, 40)  // user2's share
        );

        try {
            expenseManager.addExpense("Dinner", "EXACT", 100, user1, splits);
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }



        // Call settleDebt
        List<String> settlements = expenseManager.settleDebt();

        // Verify that the settlements are correct
        String expectedSettlement = user2.getName() + " owes " + user1.getName() + " : 40.0";
        assertTrue(settlements.contains(expectedSettlement), "Settlements should include: " + expectedSettlement);

        // Verify that there's only one settlement
        assertEquals(1, settlements.size(), "There should only be one settlement.");
    }
}

