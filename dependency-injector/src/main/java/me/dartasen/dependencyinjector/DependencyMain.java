package me.dartasen.dependencyinjector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DependencyMain {

    public static void main(String[] args) {
        log.info("Starting dependency main");

        Container container = new Container();
        Injector injector = new Injector(container);

        container.scan(DependencyMain.class);
        var result = container.getDependencyMap();

        log.info("Ending dependency main");
    }

}
