package EffectiveJava.item34;

import static EffectiveJava.item34.PayrollDay.PayType.WEEKDAY;
import static EffectiveJava.item34.PayrollDay.PayType.WEEKEND;
//import static EffectiveJava.item34.Payroll.PayTypes.WEEKDAY2;
//import static EffectiveJava.item34.Payroll.PayTypes.WEEKEND2;


// 코드 34-9 전략 열거 타입 패턴 (218-219쪽)
enum PayrollDay {
    MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY),
    THURSDAY(WEEKDAY), FRIDAY(WEEKDAY),
    SATURDAY(WEEKEND), SUNDAY(WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) { this.payType = payType; }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    // 전략 열거 타입
    enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);
        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }

    public static void main(String[] args) {
        for (PayrollDay day : values())
            System.out.printf("%-10s%d%n", day, day.pay(8 * 60, 1));
    }
}

//enum Payroll {
//    MONDAY(WEEKDAY2), TUESDAY(WEEKDAY2), WEDNESDAY(WEEKDAY2),
//    THURSDAY(WEEKDAY2), FRIDAY(WEEKDAY2),
//    SATURDAY(WEEKEND2), SUNDAY(WEEKEND2);
//
//    Payroll(PayTypes type) {
//        this.type = type;
//    }
//
//    private final PayTypes type;
//
//    int pay(int worked, int payRate){
//        return type.pay(worked, payRate);
//    }
//
//    enum PayTypes {
//        WEEKDAY2 {
//            @Override
//            int overtimePay(int worked, int payRate) {
//                return (int) ((worked - LEAST_WORKING_TIME) * payRate * 1.5);
//            }
//        },
//        WEEKEND2 {
//            @Override
//            int overtimePay(int worked, int payRate) {
//                return (int) (worked * payRate * 1.5);
//            }
//        };
//        public static final int LEAST_WORKING_TIME = 8 * 60;
//        public int pay(int worked, int payRate){
//            return worked * payRate + overtimePay(worked, payRate);
//        }
//
//        abstract int overtimePay(int worked, int payRate);
//    }
//}