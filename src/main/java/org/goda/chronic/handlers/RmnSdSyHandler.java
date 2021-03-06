package org.goda.chronic.handlers;

import java.util.List;
import org.goda.chronic.Options;
import org.goda.chronic.repeaters.RepeaterMonthName;
import org.goda.chronic.tags.ScalarDay;
import org.goda.chronic.tags.ScalarYear;
import org.goda.util.CollectionUtils;
import org.goda.chronic.utils.Time;
import org.goda.chronic.utils.Token;
import org.goda.time.DateTime;
import org.goda.time.MutableInterval;



public class RmnSdSyHandler implements IHandler {

  public MutableInterval handle(List<Token> tokens, Options options) {
    int month = tokens.get(0).getTag(RepeaterMonthName.class).getType().ordinal();
    int day = ((Number)tokens.get(1).getTag(ScalarDay.class).getType()).intValue();
    int year = ((Number)tokens.get(2).getTag(ScalarYear.class).getType()).intValue();

    MutableInterval MutableInterval;
    try {
      List<Token> timeTokens = CollectionUtils.subList(tokens,3, tokens.size());
      DateTime dayStart = Time.construct(year, month, day);
      MutableInterval = Handler.dayOrTime(dayStart, timeTokens, options);
    }
    catch (IllegalArgumentException e) {
      if (options.isDebug()) {
        e.printStackTrace(System.out);
      }
      MutableInterval = null;
    }
    return MutableInterval;
  }

}
