package 组合;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component{
    private List<Component> child;

    public Composite(String name) {
        super(name);
        child = new ArrayList<Component>();
    }

    void print(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println("Composite:" + name);
        for (Component component : child) {
            component.print(level + 1);
        }
    }

    public void add(Component component) {
        child.add(component);
    }

    public void remove(Component component) {
        child.remove(component);
    }
}
