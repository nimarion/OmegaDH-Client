package de.nimarion.photofinish.common.startlist;

import java.util.List;

public abstract class StartlistParser {

    private List<Startlist> startlists;

    public StartlistParser(String path) {
        startlists = parse(path);
    }

    public List<Startlist> getStartlists() {
        return startlists;
    }

    public Startlist getStartlist(String id) {
        return startlists.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }
    
    protected abstract List<Startlist> parse(String path);
    
}
