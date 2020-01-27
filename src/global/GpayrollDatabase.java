package global;

import java.util.HashMap;

public class GpayrollDatabase {

  private static HashMap<Integer, Employee> itsEmployees = new HashMap<>();
  private static HashMap<Integer, Employee> unionMembers = new HashMap<>();

  public static void addEmployee(int empId, Employee e) {
    itsEmployees.put(empId, e);
  }

  public static Employee getEmployee(int empId) {
    return itsEmployees.get(empId);
  }

  public static void deleteEmployee(int empId) {
    itsEmployees.remove(empId);
  }

  public static void addUnionMember(int memberId, Employee e) {
    unionMembers.put(memberId, e);
  }

  public static Employee getUnionMember(int memberId) {
    return unionMembers.get(memberId);
  }

  public static void removeUnionMember(int memberId) {
    unionMembers.remove(memberId);
  }

}
