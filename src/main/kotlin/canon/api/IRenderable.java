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
 * Classes which implements this interface can be rendered by a renderer.
 *
 * @since 1.0.0
 */
public interface IRenderable extends IVisitable {

    @Override
    default void accept(IVisitor visitor) {
        visitor.visitRenderable(this);
    }

}
