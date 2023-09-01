package ir.ac.kntu.objects;

import java.time.LocalDateTime;

public class FakeDate {
    private long year;
    private long month;
    private long day;
    private long hour;
    private long minute;
    private long second;

    public FakeDate(long year, long month, long day, long hour, long minute, long second) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public FakeDate() {
        sync();
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        if (1 <= month && month <= 12) {
            this.month = month;
        } else if (month > 0) {
            addToMonths(month);
        }
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        if (1 <= day && day <= 28) {
            this.day = day;
        } else if (day > 0) {
            addToDays(day);
        }
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        if (1 <= hour && hour <= 24) {
            this.hour = hour;
        } else if (hour > 0) {
            addToHours(hour);
        }
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        if (1 <= minute && minute < 60) {
            this.minute = minute;
        } else if (minute > 0) {
            addToMinutes(minute);
        }
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        if (1 <= second && second <= 60) {
            this.second = second;
        } else if (second > 0) {
            addToSeconds(second);
        }
    }

    public void addToSeconds(long second) {
        if (second != 0) {
            this.second += second;
            addToMinutes((int) (this.second / 60));
            this.second = this.second % 60;
        }
    }

    public void addToMinutes(long minute) {
        if (minute != 0) {
            this.minute += minute;
            addToHours((int) (this.minute / 60));
            if (this.minute % 60 != 0) {
                this.minute = this.minute % 60;
            } else {
                this.minute = 0;
            }
        }
    }

    public void addToHours(long hour) {
        if (hour != 0) {
            this.hour += hour;
            addToDays((int) (this.hour / 24));
            if (this.hour % 24 != 0) {
                this.hour = this.hour % 24;
            } else {
                this.hour = 0;
            }
        }
    }

    public void addToDays(long day) {
        if (day != 0) {
            this.day += day;
            addToMonths((int) (this.day / 28));
            if (this.day % 28 == 0) {
                this.day = 28;
            } else {
                this.day = this.day % 28;
            }
        }
    }

    public void addToMonths(long month) {
        if (month != 0) {
            this.month += month;
            this.year += (int) (this.month / 12);
            if (this.month % 12 == 0) {
                this.month = 12;
            } else {
                this.month = this.month % 12;
            }
        }
    }

    @Override
    public String toString() {
        return "FakeDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }

    public void sync() {
        setYear(LocalDateTime.now().getYear());
        setMonth(LocalDateTime.now().getMonth().getValue());
        setDay(LocalDateTime.now().getDayOfMonth());
        setHour(LocalDateTime.now().getHour());
        setMinute(LocalDateTime.now().getMinute());
        setSecond(LocalDateTime.now().getSecond());
    }
}
