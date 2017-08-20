package com.pgs.collectionsutils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;

public abstract class MockTestCase {
    private final List<Object> mockObjects = new ArrayList<Object>();
 
    @SuppressWarnings("unchecked")
    protected <T> T createMock(final Class<?> name) {
        final T mock = (T) EasyMock.createMock(name);
        return registerMock(mock);
    }
 
    private <T> T registerMock(final T mock) {
        mockObjects.add(mock);
        return mock;
    }
 
    protected <T> IExpectationSetters<T> expect(final T t) {
        return EasyMock.expect(t);
    }
 
    protected final void replay() {
        for (final Object o : mockObjects) {
            EasyMock.replay(o);
        }
    }
 
    protected final void verify() {
        for (final ListIterator<Object> i = mockObjects.listIterator(); i.hasNext();) {
            try {
                EasyMock.verify(i.next());
            } catch (final AssertionError e) {
                throw new AssertionError(i.previousIndex() + 1 + ""
                        + e.getMessage());
            }
        }
    }
}