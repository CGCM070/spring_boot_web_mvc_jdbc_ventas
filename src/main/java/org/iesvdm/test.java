package org.iesvdm;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class test {
    record Record(double total, double media) {}
    public static void main(String[] args) {



    List<Number> n = List.of(2,4,55,4,6,22,561,7);

        DoubleSummaryStatistics statistics = n.stream().mapToDouble(Number::doubleValue).summaryStatistics();
        Record r1 = new Record(statistics.getAverage(), statistics.getCount());
        System.out.println(r1);

    }
}
