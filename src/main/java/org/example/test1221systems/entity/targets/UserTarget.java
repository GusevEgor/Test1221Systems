package org.example.test1221systems.entity.targets;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum UserTarget {
    WEIGHT_LOSS,
    MAINTENANCE,
    WEIGHT_GAIN;

    public static Optional<UserTarget> fromString(String target) {
        return Arrays.stream(UserTarget.values())
                .filter(ut -> ut.name()
                        .equalsIgnoreCase(target))
                .findFirst();
    }

    public static List<String> getAllTargets() {
        return Arrays.stream(UserTarget.values())
                .map(UserTarget::name)
                .toList();
    }
}
