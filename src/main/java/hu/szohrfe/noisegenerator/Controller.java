package hu.szohrfe.noisegenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Controller {
    private final Model model;
    public void update() {
        model.update();
    }
}
