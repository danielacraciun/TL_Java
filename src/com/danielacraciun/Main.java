package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.controller.ControllerException;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.Repository;
import com.danielacraciun.repository.RepositoryException;

class Main {

    public static void main(String[] args) throws ControllerException, RepositoryException {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        //Console console = new Console(ctrl);
        //console.run();
    }
}