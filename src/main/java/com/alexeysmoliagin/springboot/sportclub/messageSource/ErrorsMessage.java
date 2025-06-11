package com.alexeysmoliagin.springboot.sportclub.messageSource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class ErrorsMessage {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UserMessage {
            public static final String USER_NOT_EXIST = "notSuchEntityException.users.notExist";
            public static final String USER_DELETE = "user.delete";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SubscriptionMessage {
        public static final String SUBSCRIPTION_NOT_EXIST = "notSuchEntityException.subscription.notExist";
        public static final String SUBSCRIPTION_DELETE = "subscription.delete";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ActivityMessage {
        public static final String ACTIVITY_NOT_EXIST = "notSuchEntityException.activity.notExist";
        public static final String ACTIVITY_DELETE = "activity.delete";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ScheduleMessage {
        public static final String SCHEDULE_NOT_EXIST = "notSuchEntityException.schedule.notExist";
        public static final String SCHEDULE_DELETE = "schedule.delete";

    }
}


