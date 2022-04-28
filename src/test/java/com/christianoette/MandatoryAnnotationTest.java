package com.christianoette;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

class MandatoryAnnotationTest {

    @Test
    void thatAllClassesAreAnnotatedIfRequired() {
        Reflections reflections = new Reflections("com.christianoette");
        Set<String> allAnnotatedTypes = reflections.get(TypesAnnotated.with(MandatoryAnnotation.class));
        Set<String> allInterfaceImplementations = reflections.get(SubTypes.of(MyInterface.class));

        Set<String> classesWithMissingAnnotations = allInterfaceImplementations
                .stream()
                .filter(c -> !allAnnotatedTypes.contains(c))
                .collect(Collectors.toSet());

        Assertions.assertThat(classesWithMissingAnnotations).isEmpty();
    }
}
