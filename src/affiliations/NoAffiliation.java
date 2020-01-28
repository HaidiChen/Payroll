package affiliations;

import intfs.Affiliation;
import global.Paycheck;

public class NoAffiliation implements Affiliation {

  public double calculateDeductions(Paycheck pc) {
    return 0;
  }
}
