package canon.parser.dsl

import canon.model.*
import canon.model.Map
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CanonDSLParserTest {

    @Test fun testBasket() {
        CanonDSLParser.parse {
            basket("id", "class", "name", true) {
                basket(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Basket)
            Assertions.assertFalse((it as Basket).renderables.isNullOrEmpty())
        }
    }

    @Test fun testBlock() {
        CanonDSLParser.parse {
            block("id", "class", "name") {
                block(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Block)
            Assertions.assertFalse((it as Block).renderables.isNullOrEmpty())
        }
    }

    @Test fun testBold() {
        CanonDSLParser.parse {
            bold("id", "class", "text")
        }.forEach {
            Assertions.assertTrue(it is Bold)
        }
    }

    @Test fun testBreak() {
        CanonDSLParser.parse {
            `break`("id", "class")
        }.forEach {
            Assertions.assertTrue(it is Break)
        }
    }

    @Test fun testButton() {
        CanonDSLParser.parse {
            button("id", "class", "text", "name", "value") {
                button(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Button)
            Assertions.assertFalse((it as Button).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCamera() {
        CanonDSLParser.parse {
            camera("id", "class", "name", true, 1.0) {
                camera(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Camera)
            Assertions.assertFalse((it as Camera).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCarousel() {
        CanonDSLParser.parse {
            carousel("id", "class", "text", "name", true) {
                carousel(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Carousel)
            Assertions.assertFalse((it as Carousel).renderables.isNullOrEmpty())
        }
    }

    @Test fun testChoice() {
        CanonDSLParser.parse {
            choice("id", "class", "name", "text", "selected") {
                choice(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Choice)
            Assertions.assertFalse((it as Choice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCodeReader() {
        CanonDSLParser.parse {
            codeReader("id", "class", "name", "format")
        }.forEach {
            Assertions.assertTrue(it is CodeReader)
        }
    }

    @Test fun testCol() {
        CanonDSLParser.parse {
            col("id", "class") {
                col(null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Col)
            Assertions.assertFalse((it as Col).renderables.isNullOrEmpty())
        }
    }

    @Test fun testContainer() {
        CanonDSLParser.parse {
            container("id", "class", "name") {
                container(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Container)
            Assertions.assertFalse((it as Container).renderables.isNullOrEmpty())
        }
    }

    @Test fun testEmail() {
        CanonDSLParser.parse {
            email("id", "class", "placeholder", true, "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Email)
        }
    }

    @Test fun testForeach() {
        CanonDSLParser.parse {
            foreach("\$forEachStmt", null)
        }.forEach {
            Assertions.assertTrue(it is Foreach)
        }
    }

    @Test fun testForm() {
        CanonDSLParser.parse {
            form("id", "class", "name") {
                form(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Form)
            Assertions.assertFalse((it as Form).renderables.isNullOrEmpty())
        }
    }

    @Test fun testHeadline() {
        CanonDSLParser.parse {
            headline("id", "class", "text")
        }.forEach {
            Assertions.assertTrue(it is Headline)
        }
    }

    @Test fun testIf() {
        CanonDSLParser.parse {
            `if`("expression", null)
        }.forEach {
            Assertions.assertTrue(it is If)
        }
    }

    @Test fun testImage() {
        CanonDSLParser.parse {
            image("id", "class", "src", "width", "height","alt")
        }.forEach {
            Assertions.assertTrue(it is Image)
        }
    }

    @Test fun testItalic() {
        CanonDSLParser.parse {
            italic("id", "class", "text")
        }.forEach {
            Assertions.assertTrue(it is Italic)
        }
    }

    @Test fun testItem() {
        CanonDSLParser.parse {
            item("id", "class") {
                item(null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Item)
            Assertions.assertFalse((it as Item).renderables.isNullOrEmpty())
        }
    }

    @Test fun testItems() {
        CanonDSLParser.parse {
            items("id", "class", false) {
                items(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Items)
            Assertions.assertFalse((it as Items).renderables.isNullOrEmpty())
        }
    }

    @Test fun testLabel() {
        CanonDSLParser.parse {
            label("id", "class", "text")
        }.forEach {
            Assertions.assertTrue(it is Label)
        }
    }

    @Test fun testLink() {
        CanonDSLParser.parse {
            link("id", "class", "value", "text")
        }.forEach {
            Assertions.assertTrue(it is Link)
        }
    }

    @Test fun testMap() {
        CanonDSLParser.parse {
            map("id", "class", "name", "src", "mapType", "centerLng",
                    "centerLat", "markerIcon", "selectedMarkerIcon",
                    "routeStartIcon", "routeEndIcon", "routePoints",
                    true, true, "zoom", 1)
        }.forEach {
            Assertions.assertTrue(it is Map)
        }
    }

    @Test fun testMultipleChoice() {
        CanonDSLParser.parse {
            multipleChoice("id", "class", "name", true, true) {
                multipleChoice(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is MultipleChoice)
            Assertions.assertFalse((it as MultipleChoice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testOverlay() {
        CanonDSLParser.parse {
            overlay("id", "class", "trigger") {
                overlay(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Overlay)
            Assertions.assertFalse((it as Overlay).renderables.isNullOrEmpty())
        }
    }

    @Test fun testOverlays() {
        CanonDSLParser.parse {
            overlays("id", "class", "trigger") {
                overlays(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Overlays)
            Assertions.assertFalse((it as Overlays).renderables.isNullOrEmpty())
        }
    }

    @Test fun testPhone() {
        CanonDSLParser.parse {
            phone("id", "class", "placeholder", true, "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Phone)
        }
    }

    @Test fun testReel() {
        CanonDSLParser.parse {
            reel("id", "class", "name") {
                reel(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Reel)
            Assertions.assertFalse((it as Reel).renderables.isNullOrEmpty())
        }
    }

    @Test fun testReelValue() {
        CanonDSLParser.parse {
            reelValue("id", "class", "value", "valueType")
        }.forEach {
            Assertions.assertTrue(it is ReelValue)
        }
    }

    @Test fun testRow() {
        CanonDSLParser.parse {
            row("id", "class") {
                row(null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Row)
        }
    }

    @Test fun testSelectable() {
        CanonDSLParser.parse {
            selectable("id", "class", "name") {
                selectable(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Selectable)
            Assertions.assertFalse((it as Selectable).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSelection() {
        CanonDSLParser.parse {
            selection("id", "class", "name", 1) {
                selection(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Selection)
            Assertions.assertFalse((it as Selection).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSelectionItem() {
        CanonDSLParser.parse {
            selectionItem("id", "class", "name") {
                selectionItem(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SelectionItem)
            Assertions.assertFalse((it as SelectionItem).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSingleChoice() {
        CanonDSLParser.parse {
            singleChoice("id", "class", "name", true, true) {
                singleChoice(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SingleChoice)
            Assertions.assertFalse((it as SingleChoice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSlider() {
        CanonDSLParser.parse {
            slider("id", "class", 1.0, 10.0, 1.0, 5.0, "name", "values")
        }.forEach {
            Assertions.assertTrue(it is Slider)
        }
    }

    @Test fun testSlotMachine() {
        CanonDSLParser.parse {
            slotMachine("id", "class", "name") {
                slotMachine(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SlotMachine)
            Assertions.assertFalse((it as SlotMachine).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSpinner() {
        CanonDSLParser.parse {
            spinner("id", "class", 1.0, 10.0, 1.0, 5.0, "name")
        }.forEach {
            Assertions.assertTrue(it is Spinner)
        }
    }

    @Test fun testSubmit() {
        CanonDSLParser.parse {
            submit("id", "class", "text", "name")
        }.forEach {
            Assertions.assertTrue(it is Submit)
        }
    }

    @Test fun testSuggestion() {
        CanonDSLParser.parse {
            suggestion("id", "class", "text", "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Suggestion)
        }
    }

    @Test fun testTable() {
        CanonDSLParser.parse {
            table("id", "class", "name") {
                table(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Table)
            Assertions.assertFalse((it as Table).renderables.isNullOrEmpty())
        }
    }

    @Test fun testText() {
        CanonDSLParser.parse {
            text("id", "class", "regex", "placeholder", true, "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Text)
        }
    }

    @Test fun testTextarea() {
        CanonDSLParser.parse {
            textarea("id", "class","placeholder", "name", "value", true, 1,1)
        }.forEach {
            Assertions.assertTrue(it is Textarea)
        }
    }

    @Test fun testTransition() {
        CanonDSLParser.parse {
            transition("id", "class", "name", "direction", "wrapped") {
                transition(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Transition)
            Assertions.assertFalse((it as Transition).renderables.isNullOrEmpty())
        }
    }

    @Test fun testTrigger() {
        CanonDSLParser.parse {
            trigger("id", "class", "name", "text")
        }.forEach {
            Assertions.assertTrue(it is Trigger)
        }
    }

    @Test fun testUpload() {
        CanonDSLParser.parse {
            upload("id", "class", "accept", "name", "text", 10.0, 10.0)
        }.forEach {
            Assertions.assertTrue(it is Upload)
        }
    }

    @Test fun testVideo() {
        CanonDSLParser.parse {
            video("id", "class", "src")
        }.forEach {
            Assertions.assertTrue(it is Video)
        }
    }
}