class Time {
    int hrs = 0;
    int mins = 0;
    int secs = 0;

    public Time() {

    }

    public void timeSet() {
        this.hrs = 0;
        this.mins = 0;
        this.secs = 0;
    }

    public void TimeAlt(int h, int m, int s) {
        this.hrs = h;
        this.mins = m;
        this.secs = s;
    }

    public void set(int h, int m, int s) {
        this.hrs = (h >= 0 && h < 24) ? h : 0;
        this.mins = (m >= 0 && m < 60) ? m : 0;
        this.secs = (s >= 0 && s < 60) ? s : 0;
    }

    public int getHours() {
        return this.hrs;
    }

    public int getMins() {
        return this.mins;
    }

    public int getSecs() {
        return this.secs;
    }

    public void printMilitia() {
        if (this.hrs >= 10) {
            System.out.print("" + this.hrs + ": ");
        } else {
            System.out.print("0" + this.hrs + ": ");
        }
        if (this.mins >= 10) {
            System.out.print("" + this.mins + ": ");
        } else {
            System.out.print("0" + this.mins + ": ");
        }
        if (this.secs >= 10) {
            System.out.println("" + this.secs + " ");
        } else {
            System.out.println("0" + this.secs + " ");
        }

        /*
         * System.out.print((this.hrs < 10? "0": "") + this.hrs + ":");
         * System.out.print((this.mins < 10? "0": "") + this.mins + ":");
         * System.out.print((this.secs < 10? "0": "") + this.secs);
         */
    }

    public void printStandard() {
        if (this.hrs == 0 || this.hrs == 12) {
            System.out.print("12" + ": ");
        } else {
            System.out.print("" + (this.hrs % 12) + ": ");
        }
        if (this.mins < 10) {
            System.out.print("0" + this.mins + ": ");
        } else {
            System.out.print("" + this.mins + ": ");
        }
        if (this.secs < 10) {
            System.out.print("0" + this.secs + " ");
        } else {
            System.out.print("" + this.secs + " ");
        }
        if (this.hrs > 12) {
            System.out.println(" PM");
        } else {
            System.out.println(" AM");
        }

        /*
         * System.out.print((this.hrs == 0 || this.hrs == 12? 12: this.hrs % 12) + ":");
         * System.out.print((this.mins < 10? "0": "") + this.mins + ":");
         * System.out.print((this.secs < 10? "0": "") + this.secs + " ");
         * System.out.print((this.hrs < 12? "AM": "PM"));
         */
    }

    public String toString() {
        return this.hrs + ":" + this.mins + ":" + this.secs;
    }

    public boolean equals(Time t) {
        return (t.hrs == this.hrs && t.mins == this.mins && t.secs == this.secs);
    }

    public boolean doesNotEqual(Time t) {
        return (t.hrs != this.hrs && t.mins != this.mins && t.secs != this.secs);
    }

    public void copy(Time t) {
        this.hrs = t.hrs;
        this.mins = t.mins;
        this.secs = t.secs;
    }

    public Time getCopy() {
        Time t = new Time();
        t.hrs = this.hrs;
        t.mins = this.mins;
        t.secs = this.secs;

        return t;
    }

    public void advanceSecs() {
        if (this.secs == 59) {
            this.secs = 0;
            advanceMins();
        } else {
            this.secs++;
        }
    }

    public void advanceMins() {
        if (this.mins == 59) {
            this.mins = 0;
            advanceHrs();
        } else {
            this.mins++;
        }
    }

    public void advanceHrs() {
        if (this.hrs == 24) {
            this.hrs = 0;
        } else {
            this.hrs++;
        }
    }

    public boolean lessThan(Time t) {
        return (this.hrs < t.hrs || this.hrs == t.hrs && this.mins < t.mins
                || this.hrs == t.hrs && this.mins == t.mins && this.secs < t.secs);
    }

    public boolean lessOrE(Time t) {
        return (this.hrs <= t.hrs || this.hrs == t.hrs && this.mins <= t.mins
                || this.hrs == t.hrs && this.mins == t.mins && this.secs <= t.secs);
    }

    public boolean greaterThan(Time t) {
        return (this.hrs > t.hrs || this.hrs == t.hrs && this.mins > t.mins
                || this.hrs == t.hrs && this.mins == t.mins && this.secs > t.secs);
    }

    public boolean greaterOrE(Time t) {
        return (this.hrs >= t.hrs || this.hrs == t.hrs && this.mins >= t.mins
                || this.hrs == t.hrs && this.mins == t.mins && this.secs >= t.secs);
    }

    public String toMilitia() {
        String resultString = "";
        if (this.hrs >= 10) {
            resultString += ("" + this.hrs + ": ");
        } else {
            resultString += ("0" + this.hrs + ": ");
        }
        if (this.mins >= 10) {
            resultString += ("" + this.mins + ": ");
        } else {
            resultString += ("0" + this.mins + ": ");
        }
        if (this.secs >= 10) {
            resultString += ("" + this.secs + " ");
        } else {
            resultString += ("0" + this.secs + " ");
        }

        return resultString;
    }

    public String toStandard() {
        String resultString = "";
        if (this.hrs == 0 || this.hrs == 12) {
            resultString += ("12" + ": ");
        } else {
            resultString += ("" + (this.hrs % 12) + ": ");
        }
        if (this.mins < 10) {
            resultString += ("0" + this.mins + ": ");
        } else {
            resultString += ("" + this.mins + ": ");
        }
        if (this.secs < 10) {
            resultString += ("0" + this.secs + " ");
        } else {
            resultString += ("" + this.secs + " ");
        }
        if (this.hrs > 12) {
            resultString += (" PM");
        } else {
            resultString += (" AM");
        }

        return resultString;
    }

}