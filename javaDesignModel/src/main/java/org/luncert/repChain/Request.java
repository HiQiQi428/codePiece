package org.luncert.repChain;

public class Request {

    private String name;

    private String reason;

    private int days;

    private String info;

    public Request(Builder builder) {
        name = builder.name;
        reason = builder.reason;
        days = builder.days;
        info = builder.info;
    }

    public static class Builder {
        private String name;
        private String reason;
        private int days;
        private String info;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setDays(int days) {
            this.days = days;
            return this;
        }

        public Builder setInfo(String info) {
            this.info = info;
            return this;
        }

        public Builder newRequest(Request request) {
            name = request.name;
            days = request.days;
            reason = request.reason;
            if (request.info != null && !request.info.equals("")) info = request.info;
            return this;
        }
        
        public Request build() {
            return new Request(this);
        }
    }

    public String getName() { return name; }
    public String getReason() { return reason; }
    public int getDays() { return days; }
    public void setInfo(String info) { this.info = info; }
    public String getInfo() { return info; }

    public String toString() {
        return new StringBuilder()
                    .append("Request [name = ").append(name)
                    .append(", reason = ").append(reason)
                    .append(", days = ").append(days)
                    .append(", info = ").append(info)
                    .append("]")
                    .toString();
    }

}