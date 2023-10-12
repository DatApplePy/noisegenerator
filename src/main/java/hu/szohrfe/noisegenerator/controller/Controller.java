package hu.szohrfe.noisegenerator.controller;

import hu.szohrfe.noisegenerator.model.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Controller {
    private final Model model;
    public void update() {
        model.update();
    }
}
