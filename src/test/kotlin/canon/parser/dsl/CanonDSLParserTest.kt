package canon.parser.dsl

import canon.model.*
import canon.model.Map
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CanonDSLParserTest {

    @Test fun testBasket() {
        CanonDSLParser.parse {
            basket("id", "class", "ariaLabel", "name", true) {
                basket(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Basket)
            Assertions.assertFalse((it as Basket).renderables.isNullOrEmpty())
        }
    }

    @Test fun testBlock() {
        CanonDSLParser.parse {
            block("id", "class", "ariaLabel", "name") {
                block(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Block)
            Assertions.assertFalse((it as Block).renderables.isNullOrEmpty())
        }
    }

    @Test fun testBold() {
        CanonDSLParser.parse {
            bold("id", "class", "ariaLabel", "text")
        }.forEach {
            Assertions.assertTrue(it is Bold)
        }
    }

    @Test fun testBreak() {
        CanonDSLParser.parse {
            `break`("id", "ariaLabel", "class")
        }.forEach {
            Assertions.assertTrue(it is Break)
        }
    }

    @Test fun testButton() {
        CanonDSLParser.parse {
            button("id", "class", "ariaLabel", "text", "name", "value") {
                button(null, null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Button)
            Assertions.assertFalse((it as Button).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCamera() {
        CanonDSLParser.parse {
            camera("id", "class", "ariaLabel", "name", true, 1.0) {
                camera(null, null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Camera)
            Assertions.assertFalse((it as Camera).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCarousel() {
        CanonDSLParser.parse {
            carousel("id", "class", "ariaLabel", "text", "name", true) {
                carousel(null, null, null,null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Carousel)
            Assertions.assertFalse((it as Carousel).renderables.isNullOrEmpty())
        }
    }

    @Test fun testChoice() {
        CanonDSLParser.parse {
            choice("id", "class", "ariaLabel", "name", "text", "selected") {
                choice(null, null, null,  null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Choice)
            Assertions.assertFalse((it as Choice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testCodeReader() {
        CanonDSLParser.parse {
            codeReader("id", "class", "ariaLabel", "name", "format")
        }.forEach {
            Assertions.assertTrue(it is CodeReader)
        }
    }

    @Test fun testCol() {
        CanonDSLParser.parse {
            col("id", "class", "ariaLabel") {
                col(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Col)
            Assertions.assertFalse((it as Col).renderables.isNullOrEmpty())
        }
    }

    @Test fun testContainer() {
        CanonDSLParser.parse {
            container("id", "class", "ariaLabel", "name") {
                container(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Container)
            Assertions.assertFalse((it as Container).renderables.isNullOrEmpty())
        }
    }

    @Test fun testEmail() {
        CanonDSLParser.parse {
            email("id", "class", "ariaLabel", "placeholder", true, "name", "value")
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
            form("id", "class", "ariaLabel", "name") {
                form(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Form)
            Assertions.assertFalse((it as Form).renderables.isNullOrEmpty())
        }
    }

    @Test fun testHeadline() {
        CanonDSLParser.parse {
            headline("id", "class", "ariaLabel", "text", "1")
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
            image("id", "class", "ariaLabel", "src", "width", "height","alt")
        }.forEach {
            Assertions.assertTrue(it is Image)
        }
    }

    @Test fun testItalic() {
        CanonDSLParser.parse {
            italic("id", "class", "ariaLabel", "text")
        }.forEach {
            Assertions.assertTrue(it is Italic)
        }
    }

    @Test fun testItem() {
        CanonDSLParser.parse {
            item("id", "class", "ariaLabel") {
                item(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Item)
            Assertions.assertFalse((it as Item).renderables.isNullOrEmpty())
        }
    }

    @Test fun testItems() {
        CanonDSLParser.parse {
            items("id", "class", "ariaLabel", false) {
                items(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Items)
            Assertions.assertFalse((it as Items).renderables.isNullOrEmpty())
        }
    }

    @Test fun testLabel() {
        CanonDSLParser.parse {
            label("id", "class", "ariaLabel", "text")
        }.forEach {
            Assertions.assertTrue(it is Label)
        }
    }

    @Test fun testLink() {
        CanonDSLParser.parse {
            link("id", "class", "ariaLabel", "value", "text")
        }.forEach {
            Assertions.assertTrue(it is Link)
        }
    }

    @Test fun testMap() {
        CanonDSLParser.parse {
            map("id", "class", "ariaLabel", "name", "src", "mapType", "centerLng",
                    "centerLat", "markerIcon", "selectedMarkerIcon",
                    "routeStartIcon", "routeEndIcon", "routePoints",
                    true, true, "zoom", 1, "35")
        }.forEach {
            Assertions.assertTrue(it is Map)
        }
    }

    @Test fun testMultipleChoice() {
        CanonDSLParser.parse {
            multipleChoice("id", "class", "ariaLabel", "name", true, true) {
                multipleChoice(null, null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is MultipleChoice)
            Assertions.assertFalse((it as MultipleChoice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testOverlay() {
        CanonDSLParser.parse {
            overlay("id", "class", "ariaLabel", "trigger") {
                overlay(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Overlay)
            Assertions.assertFalse((it as Overlay).renderables.isNullOrEmpty())
        }
    }

    @Test fun testOverlays() {
        CanonDSLParser.parse {
            overlays("id", "class", "ariaLabel", "trigger") {
                overlays(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Overlays)
            Assertions.assertFalse((it as Overlays).renderables.isNullOrEmpty())
        }
    }

    @Test fun testPhone() {
        CanonDSLParser.parse {
            phone("id", "class", "ariaLabel", "placeholder", true, "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Phone)
        }
    }

    @Test fun testReel() {
        CanonDSLParser.parse {
            reel("id", "class", "ariaLabel", "name") {
                reel(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Reel)
            Assertions.assertFalse((it as Reel).renderables.isNullOrEmpty())
        }
    }

    @Test fun testReelValue() {
        CanonDSLParser.parse {
            reelValue("id", "class", "ariaLabel", "value", "valueType")
        }.forEach {
            Assertions.assertTrue(it is ReelValue)
        }
    }

    @Test fun testRow() {
        CanonDSLParser.parse {
            row("id", "class", "ariaLabel",) {
                row(null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Row)
        }
    }

    @Test fun testSelectable() {
        CanonDSLParser.parse {
            selectable("id", "class", "ariaLabel", "name") {
                selectable(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Selectable)
            Assertions.assertFalse((it as Selectable).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSelection() {
        CanonDSLParser.parse {
            selection("id", "class", "ariaLabel", "name", 1) {
                selection(null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Selection)
            Assertions.assertFalse((it as Selection).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSelectionItem() {
        CanonDSLParser.parse {
            selectionItem("id", "class", "ariaLabel", "name") {
                selectionItem(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SelectionItem)
            Assertions.assertFalse((it as SelectionItem).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSingleChoice() {
        CanonDSLParser.parse {
            singleChoice("id", "class", "ariaLabel", "name", true, true) {
                singleChoice(null, null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SingleChoice)
            Assertions.assertFalse((it as SingleChoice).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSlider() {
        CanonDSLParser.parse {
            slider("id", "class", "ariaLabel", 1.0, 10.0, 1.0, 5.0, "name", "values")
        }.forEach {
            Assertions.assertTrue(it is Slider)
        }
    }

    @Test fun testSlotMachine() {
        CanonDSLParser.parse {
            slotMachine("id", "class", "ariaLabel", "name") {
                slotMachine(null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is SlotMachine)
            Assertions.assertFalse((it as SlotMachine).renderables.isNullOrEmpty())
        }
    }

    @Test fun testSpinner() {
        CanonDSLParser.parse {
            spinner("id", "class", "ariaLabel", 1.0, 10.0, 1.0, 5.0, "name")
        }.forEach {
            Assertions.assertTrue(it is Spinner)
        }
    }

    @Test fun testSubmit() {
        CanonDSLParser.parse {
            submit("id", "class", "ariaLabel", "text", "name")
        }.forEach {
            Assertions.assertTrue(it is Submit)
        }
    }

    @Test fun testSuggestion() {
        CanonDSLParser.parse {
            suggestion("id", "class", "ariaLabel", "text", "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Suggestion)
        }
    }

    @Test fun testTable() {
        CanonDSLParser.parse {
            table("id", "class", "ariaLabel", "name") {
                table(null, null, null,  null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Table)
            Assertions.assertFalse((it as Table).renderables.isNullOrEmpty())
        }
    }

    @Test fun testText() {
        CanonDSLParser.parse {
            text("id", "class", "ariaLabel", "regex", "placeholder", true, "name", "value")
        }.forEach {
            Assertions.assertTrue(it is Text)
        }
    }

    @Test fun testTextarea() {
        CanonDSLParser.parse {
            textarea("id", "class", "ariaLabel","placeholder", "name", "value", true, 1,1)
        }.forEach {
            Assertions.assertTrue(it is Textarea)
        }
    }

    @Test fun testTransition() {
        CanonDSLParser.parse {
            transition("id", "class", "ariaLabel", "name", "direction", "wrapped") {
                transition(null, null, null, null, null, null, null)
            }
        }.forEach {
            Assertions.assertTrue(it is Transition)
            Assertions.assertFalse((it as Transition).renderables.isNullOrEmpty())
        }
    }

    @Test fun testTrigger() {
        CanonDSLParser.parse {
            trigger("id", "class", "ariaLabel",  "name", "text")
        }.forEach {
            Assertions.assertTrue(it is Trigger)
        }
    }

    @Test fun testUpload() {
        CanonDSLParser.parse {
            upload("id", "class", "ariaLabel", "accept", "name", "text", 10.0, 10.0, false)
        }.forEach {
            Assertions.assertTrue(it is Upload)
        }
    }

    @Test fun testVideo() {
        CanonDSLParser.parse {
            video("id", "class", "ariaLabel", "src")
        }.forEach {
            Assertions.assertTrue(it is Video)
        }
    }
}