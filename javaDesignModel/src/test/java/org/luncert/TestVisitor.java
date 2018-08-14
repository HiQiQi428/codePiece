package org.luncert;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.visitorModel.Employee;
import org.luncert.visitorModel.FADepartment;
import org.luncert.visitorModel.GeneralEmployee;
import org.luncert.visitorModel.ManagerEmployee;

@RunWith(JUnit4.class)
public class TestVisitor {

    @Test
    public void test() {
        List<Employee> lst = new ArrayList<>();
        Employee mep1, mep2, gep1, gep2, gep3;
        mep1 = new ManagerEmployee("王总", 8, 20000, 10);
        mep2 = new ManagerEmployee("谢经理", 8, 15000, 15);
        gep1 = new GeneralEmployee("小王", 8, 8000, 8);
        gep2 = new GeneralEmployee("小李", 8, 8500, 12);
        gep3 = new GeneralEmployee("小张", 8, 7500, 0);
        lst.add(mep1);
        lst.add(mep2);
        lst.add(gep1);
        lst.add(gep2);
        lst.add(gep3);

        FADepartment faDepartment = new FADepartment();
        for (Employee employee : lst) {
            employee.accept(faDepartment);
            // faDepartment.visit(employee);
            // 不能用上面这种，FADepartment不能没有visit(Employee)方法
        }
    }

}