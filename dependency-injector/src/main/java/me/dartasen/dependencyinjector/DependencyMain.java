package me.dartasen.dependencyinjector;

import lombok.extern.slf4j.Slf4j;
import me.dartasen.dependencyinjector.models.IContainer;

@Slf4j
public class DependencyMain {

    public static void main(String[] args) {
        log.info("Starting dependency main");

        IContainer container = new Container();
        Injector injector = new Injector(container);



        log.info("Ending dependency main");
    }
}
