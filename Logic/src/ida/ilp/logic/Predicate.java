/*
 * Copyright (c) 2015 Ondrej Kuzelka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ida.ilp.logic;

import ida.utils.tuples.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 07.03.2017.
 */
public class Predicate {
    private final String name;
    private final int arity;

    public Predicate(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }

    public String getName() {
        return name;
    }

    public int getArity() {
        return arity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        if (arity != predicate.arity) return false;
        return name.equals(predicate.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (arity ^ (arity >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return name + '/' + arity;
    }

    public static Predicate create(String predicate, int arity) {
        return new Predicate(predicate,arity);
    }

    public static class PredicateFactory {
        private static final Map<Pair<String, Integer>, Predicate> map = new HashMap<>();

        public static Predicate create(String name, int arity) {
            synchronized (map) {
                Pair<String, Integer> key = new Pair<>(name, arity);
                if (!map.containsKey(key)) {
                    map.put(key, new Predicate(name, arity));
                }
                return map.get(key);
            }
        }
    }

}
