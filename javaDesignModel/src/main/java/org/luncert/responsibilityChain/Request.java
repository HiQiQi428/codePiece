package org.luncert.responsibilityChain;

public class Request {

    private String name;

    private String reason;

    private int days;

    private String groupLeaderInfo;

    private String managerInfo;

    private String departmentHeaderInfo;

    private String customInfo;

    public Request(Builder builder) {
        name = builder.name;
        reason = builder.reason;
        days = builder.days;
        groupLeaderInfo = builder.groupLeaderInfo;
        managerInfo = builder.managerInfo;
        departmentHeaderInfo = builder.departmentHeaderInfo;
        customInfo = builder.customInfo;
    }

    public static class Builder {
        private String name;
        private String reason;
        private int days;
        private String groupLeaderInfo;
        private String managerInfo;
        private String departmentHeaderInfo;
        private String customInfo;

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

        public Builder setGroupLeaderInfo(String groupLeaderInfo) {
            this.groupLeaderInfo = groupLeaderInfo;
            return this;
        }

        public Builder setManagerInfo(String managerInfo) {
            this.managerInfo = managerInfo;
            return this;
        }

        public Builder setDepartmentHeaderInfo(String departmentHeaderInfo) {
            this.departmentHeaderInfo = departmentHeaderInfo;
            return this;
        }

        public Builder setCustomInfo(String customInfo) {
            this.customInfo = customInfo;
            return this;
        }

        public Builder newRequest(Request request) {
            name = request.name;
            days = request.days;
            reason = request.reason;
            if (request.groupLeaderInfo != null && !request.groupLeaderInfo.equals("")) groupLeaderInfo = request.groupLeaderInfo;
            if (request.managerInfo != null && !request.managerInfo.equals("")) managerInfo = request.managerInfo;
            if (request.departmentHeaderInfo != null && !request.departmentHeaderInfo.equals("")) departmentHeaderInfo = request.departmentHeaderInfo;
            if (request.customInfo != null && !request.customInfo.equals("")) customInfo = request.customInfo;
            return this;
        }
        
        public Request build() {
            return new Request(this);
        }
    }

    public String getName() { return name; }
    public String getReason() { return reason; }
    public int getDays() { return days; }
    public String getGroupLeaderInfo() { return groupLeaderInfo; }
    public String getManagerInfo() { return managerInfo; }
    public String getDepartmentHeaderInfo() { return departmentHeaderInfo; }
    public String getCustomInfo() { return customInfo; }

    public String toString() {
        return new StringBuilder()
                    .append("Request [name = ").append(name)
                    .append(", reason = ").append(reason)
                    .append(", days = ").append(days)
                    .append(", groupLeaderInfo = ").append(groupLeaderInfo)
                    .append(", managerInfo = ").append(managerInfo)
                    .append(", departmentInfo = ").append(departmentHeaderInfo)
                    .append(", customInfo = ").append(customInfo)
                    .append("]")
                    .toString();
    }

}