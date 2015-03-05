package com.github.jerosch.mvelrepl;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.SimpleVariableResolverFactory;

public class MvelRepl {

    private static final List<String> EXIT_COMMANDS = Arrays.asList("exit", "quit", "bye");

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        VariableResolverFactory variableResolver = new SimpleVariableResolverFactory(new HashMap<String, Object>());
        while (true) {
            System.out.print("MVEL> ");
            String input = reader.readLine().trim();
            if (EXIT_COMMANDS.contains(input)) {
                break;
            }
            try {
                System.out.println(MVEL.eval(input, variableResolver));
            } catch (CompileException e) {
                System.out.println(format("Compile-Error: %s", e.getMessage()));
            } catch (Exception e) {
                System.out.print("ERROR: ");
                e.printStackTrace(System.out);
            }
        }
        reader.close();
    }
}
