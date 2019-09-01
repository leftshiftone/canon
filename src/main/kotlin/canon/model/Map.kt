package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Map(id: String,
          `class`: String,
          val name:String,
          val src: String,
          val mapType:String,
          val centerLng: String,
          val centerLat: String,
          val exact:Boolean,
          val centerBrowserLocation:Boolean) : AbstractRenderable(id, `class`), IRenderable
