package org.example.barriga.domain.exceptions;

import java.io.Serial;

public class ValidationException extends RuntimeException {

    // Required attribute for classes that implement Serializable
    // Unique signature to ensure compatibility during serialization
    // SerialVersionUID is used to verify that the loaded version of the class is compatible with the serialized version
    // Serialization is the process of converting an object into a sequence of bytes for storage or transmission
    // Similarly, serialization is like turning an object into a “recipe” that can be stored or sent
    // In other words, it is the “recipe” for the object that can be used to recreate it later
    // If the “recipe” changes (i.e., the class changes) and the SerialVersionUID is not updated, there may be problems when deserializing
    // Therefore, setting an explicit SerialVersionUID helps avoid these problems
    // Thinking about a cake recipe, if you change the ingredients or the preparation method, the recipe will be different
    // And if someone tries to make the cake with the old recipe, the result may not be as expected
    // Thus, SerialVersionUID helps ensure that the “recipe” used to create the object is the same as the expected “recipe”

    @Serial
    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}
