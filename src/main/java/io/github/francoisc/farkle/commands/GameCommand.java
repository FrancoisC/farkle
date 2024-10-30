package io.github.francoisc.farkle.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GameCommand {

    public static final String INIT_GAME_MESSAGE = "Welcome to Farkle, %s";
    private static final String INIT_GAME_METHOD_KEY = "init";
    private static final String INIT_GAME_METHOD_VALUE = "Initialisation d'une nouvelle partie";

    @ShellMethod(key = INIT_GAME_METHOD_KEY, value = INIT_GAME_METHOD_VALUE)
    public String initGame(@ShellOption(defaultValue = "Bob") String playerName) {
        return String.format(INIT_GAME_MESSAGE, playerName);
    }
}
