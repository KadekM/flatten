package flatten

trait Part09 {

  // Most well-known typeclasses in Play are the ones for JSON: Reads, Writes and Format
  // Play provides some `instances` of them: Format[String], Reads[DateTime], etc.
  // You can define `instances` of these type classes for your own classes.

  // Exercise: without looking at the previous part, create a type class `Serializable`, a function `toBytes` that impicitly uses this
  // typeclass, and instances for `Int` and `String`.

  object Glue {
    trait Serializable[A] {
      def serialize(x: A): Array[Byte]
    }

    def toBytes[A](x:A)(implicit ser: Serializable[A]): Array[Byte] = ser.serialize(x)
    def toBytes2[A : Serializable](x:A): Array[Byte] = implicitly[Serializable[A]].serialize(x)

    implicit val IntSerialize = new Serializable[Int] {
      def serialize(x: Int): Array[Byte] = x.toString.getBytes
    }

    implicit val StringSerialize = new Serializable[String] {
      def serialize(x: String): Array[Byte] = x.getBytes
    }

    val myString = "Bananaphone!"
    val myStringBytes = toBytes(myString)
  }




}