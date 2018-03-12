package io.github.dhafiro.paul;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class TaskPromptProvider implements PromptProvider{
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("paul@planner#>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
