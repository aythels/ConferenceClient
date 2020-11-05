package ui.renderers;

import ui.UIContext;

import java.util.Scanner;

public class ConsoleRenderer {
    private UIContext context;

    public ConsoleRenderer(UIContext context) {
        this.context = context;
    }

    public void blit() {
        for (int i = 0; i < 80; i++) {
            System.out.println();
        }
        System.out.println(
                context.getCurrentView().render()
        );
    }

    public void promptInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        context.getCurrentView().handleInput(input);
    }
}
