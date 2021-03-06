package gwt.material.design.client.data.component;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * @author Ben Dol
 */
public class Component<E extends Widget> {

    private E element;
    private boolean redraw;

    private Components<Component<?>> children;

    public Component() {}

    public Component(E element, boolean redraw) {
        this.element = element;
        this.redraw = redraw;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public boolean isRendered() {
        return element != null && element.getParent() != null;
    }

    public boolean isRedraw() {
        return redraw;
    }

    public void setRedraw(boolean redraw) {
        this.redraw = redraw;
    }

    public boolean isDrawable() {
        return element != null;
    }

    public Components<Component<?>> getChildren() {
        return children != null ? children : new Components<>();
    }

    public void add(Component<?> child) {
        if(children == null) {
            children = new Components<>();
        }

        assert !child.equals(this) : "Attempting to add Component to itself.";
        children.add(child);
    }

    public void addAll(List<Component<?>> children) {
        if(this.children == null) {
            this.children = new Components<>();
        }

        for(Component<?> child : children) {
            add(child);
        }
    }

    public void remove(Component<?> child) {
        if(children != null) {
            children.remove(child);
        }
    }

    public void destroyChildren() {
        if(children != null) {
            children.clear();
        }
    }

    public void removeFromParent() {
        if(element != null) {
            element.removeFromParent();
        }

        // clear children
        destroyChildren();
    }

    protected void clearElement() {
        if(element != null) {
            element.removeFromParent();
            element = null;
        }

        // clear children
        destroyChildren();
    }
}
