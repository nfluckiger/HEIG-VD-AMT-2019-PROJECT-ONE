package ch.heigvd.amt.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OfficialTest {

    @Test
    public void itShouldBePossibleToCreateAOfficial() {

        assertEquals("oliechti", note.getAuthor().getUsername());
        assertEquals("hello world", note.getContent());
        assertEquals(99, note.getId());
        assertFalse(note.isDeleted());
    }
}