package org.sympatic.timber.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TimberState {

    ENABLED("enabled"),
    DISABLED("disabled");

    @Getter private final String string;

}
