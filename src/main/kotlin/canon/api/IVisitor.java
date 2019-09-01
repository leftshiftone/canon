/*
 * Copyright (c) 2016-2018, Leftshift One
 * __________________
 * [2018] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

package canon.api;

import java.util.HashMap;
import java.util.Map;

/**
 * This interface defines the visitor class of the visitor pattern.
 *
 * @since 1.0.0
 */
public interface IVisitor {

    void visitRenderable(IRenderable renderable);

    default Map<String, Object> getContext() {
        return new HashMap<>();
    }

    default IVisitor wrap(Map<String, Object> context) {
        return this;
    }

}
