package be.uantwerpen.fti.ei.Tests;

import be.uantwerpen.fti.ei.DB.*;
import be.uantwerpen.fti.ei.Expense.*;
import be.uantwerpen.fti.ei.ExpenseManager.*;
import be.uantwerpen.fti.ei.Split.*;
import be.uantwerpen.fti.ei.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class IntegrationTest {

    private ExpenseManager expenseManager;
    private UserDB userDB;
    private ExpenseDB expenseDB;
    private User user1, user2;

    @BeforeEach
    void setUp() {
        userDB = UserDB.getInstance(); // Assuming these are singleton instances
        expenseDB = ExpenseDB.getInstance();
        expenseManager = new ExpenseManager(userDB, expenseDB);
    }

    @Test
    void testExpenseWorkflow() {
        // Add users
        user1 = new User("John");
        user2 = new User("Jane");
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        assertEquals(2, userDB.getData().size(), "Users should be added to UserDB");

        /*
        // EQUAL EXPENSE WORKFLOW TEST
        // Create an equal expense
        double totalAmount = 100.0;
        List<Split> splits = Arrays.asList(
                new EqualSplit(user1),
                new EqualSplit(user2)
        );
        try {
            expenseManager.addExpense("Dinner", "EQUAL", totalAmount, user1, splits);
        } catch (Exception e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }

        // Check that the expense is added to ExpenseDB
        List<Expense> expenses = expenseDB.getData();
        assertEquals(1, expenses.size(), "One expense should be added to ExpenseDB");
        Expense addedExpense = expenses.get(0);
        assertEquals("Dinner", addedExpense.getName(), "Expense name should match");
        assertEquals(totalAmount, addedExpense.getAmount(), "Expense amount should match");

        // Settle debts and check the settlements
        List<String> settlements = expenseManager.settleDebt();
        double expectedDebtAmount = Math.round(totalAmount / 2.0);
        String expectedSettlement = user2.getName() + " owes " + user1.getName() + " : " + expectedDebtAmount;
        assertTrue(settlements.contains(expectedSettlement), "Settlements should include: " + expectedSettlement);
        assertEquals(1, settlements.size(), "There should only be one settlement.");
        */

        // EXACT EXPENSE WORKFLOW TEST
        // Create an exact expense
        double totalAmount = 50.0;
        List<Split> splits = Arrays.asList(
                new ExactSplit(user1, 30.0), // user1's share
                new ExactSplit(user2, 20.0)  // user2's share
        );
        try {
            expenseManager.addExpense("Office Supplies", "EXACT", totalAmount, user1, splits);
        } catch (Exception e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }

        // Check that the expense is added to ExpenseDB
        List<Expense> expenses = expenseDB.getData();
        assertEquals(1, expenses.size(), "One expense should be added to ExpenseDB");
        Expense addedExpense = expenses.get(0);
        assertEquals("Office Supplies", addedExpense.getName(), "Expense name should match");
        assertEquals(totalAmount, addedExpense.getAmount(), "Expense amount should match");

        // Settle debts and check the settlements
        List<String> settlements = expenseManager.settleDebt();
        String expectedSettlement = user2.getName() + " owes " + user1.getName() + " : " + 20.0;
        assertTrue(settlements.contains(expectedSettlement), "Settlements should include: " + expectedSettlement);
        assertEquals(1, settlements.size(), "There should only be one settlement.");

    }
}
