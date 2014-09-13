package org.spongepowered.api;

import org.junit.Test;
import org.spongepowered.api.component.*;

import java.util.*;

import static org.junit.Assert.fail;

public class ComponentTest {
    @Test
    public void testComponent() {

    }
}

class TestObject {}

class TestManager implements ComponentManager<TestObject> {
    private final Set<ComponentSystem<TestObject>> systems = new HashSet<ComponentSystem<TestObject>>();
    private final Map<TestObject, Map<ComponentKey<? extends Component>, Component>> components = new HashMap<TestObject, Map<ComponentKey<? extends Component>, Component>>();

    @Override
    public void addSystem(Class<? extends ComponentSystem<TestObject>> system) {
        try {
            systems.add(system.newInstance());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    public ComponentSystem<TestObject> removeSystem(Class<? extends ComponentSystem<TestObject>> system) {
        if (system == null) {
            return null;
        }

        final Iterator<ComponentSystem<TestObject>> iterator = systems.iterator();
        ComponentSystem<TestObject> old = null;

        while (iterator.hasNext()) {
            final ComponentSystem<TestObject> entry = iterator.next();
            if (entry.getClass() == system) {
                iterator.remove();
                old = entry;
                break;
            }
        }

        return old;
    }

    @Override
    public <C extends Component> C addComponent(TestObject holder, ComponentKey<C> key, C instance) {
        return null;
    }

    @Override
    public <C extends Component> C removeComponent(TestObject holder, ComponentKey<C> key) {
        return null;
    }
}

class TestSystem implements ComponentSystem<TestObject> {

    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public boolean shouldProcess() {
        return false;
    }

    @Override
    public boolean offer(TestObject holder) {
        return false;
    }

    @Override
    public void preProcess() {

    }

    @Override
    public void process(TestObject holder, float dt) {

    }

    @Override
    public void postProcess() {

    }

    @Override
    public Collection<TestObject> getAll() {
        return null;
    }
}

class AComponent implements Component {
    public int a() {
        return 5;
    }
}

class BComponent implements Component {
    public int b() {
        return 5;
    }
}

class CComponent implements Component {
    public int c() {
        return 6;
    }
}
