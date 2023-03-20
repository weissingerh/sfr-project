/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package at.technikum;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;
import java.util.Optional;
/** Top tracks provided by last fm api */
@org.apache.avro.specific.AvroGenerated
public class TrackCountPerArtist extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7389723665512755554L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TrackCountPerArtist\",\"namespace\":\"at.technikum\",\"doc\":\"Top tracks provided by last fm api\",\"fields\":[{\"name\":\"artist\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"count\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TrackCountPerArtist> ENCODER =
      new BinaryMessageEncoder<TrackCountPerArtist>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TrackCountPerArtist> DECODER =
      new BinaryMessageDecoder<TrackCountPerArtist>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<TrackCountPerArtist> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<TrackCountPerArtist> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<TrackCountPerArtist> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TrackCountPerArtist>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this TrackCountPerArtist to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a TrackCountPerArtist from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a TrackCountPerArtist instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static TrackCountPerArtist fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String artist;
   private int count;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TrackCountPerArtist() {}

  /**
   * All-args constructor.
   * @param artist The new value for artist
   * @param count The new value for count
   */
  public TrackCountPerArtist(java.lang.String artist, java.lang.Integer count) {
    this.artist = artist;
    this.count = count;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return artist;
    case 1: return count;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: artist = (java.lang.String)value$; break;
    case 1: count = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'artist' field.
   * @return The value of the 'artist' field.
   */
  public java.lang.String getArtist() {
    return artist;
  }

  /**
   * Gets the value of the 'artist' field as an Optional<java.lang.String>.
   * @return The value wrapped in an Optional&lt;java.lang.String&gt;.
   */
  public Optional<java.lang.String> getOptionalArtist() {
    return Optional.<java.lang.String>ofNullable(artist);
  }

  /**
   * Sets the value of the 'artist' field.
   * @param value the value to set.
   */
  public void setArtist(java.lang.String value) {
    this.artist = value;
  }

  /**
   * Gets the value of the 'count' field.
   * @return The value of the 'count' field.
   */
  public int getCount() {
    return count;
  }

  /**
   * Gets the value of the 'count' field as an Optional<java.lang.Integer>.
   * @return The value wrapped in an Optional&lt;java.lang.Integer&gt;.
   */
  public Optional<java.lang.Integer> getOptionalCount() {
    return Optional.<java.lang.Integer>ofNullable(count);
  }

  /**
   * Sets the value of the 'count' field.
   * @param value the value to set.
   */
  public void setCount(int value) {
    this.count = value;
  }

  /**
   * Creates a new TrackCountPerArtist RecordBuilder.
   * @return A new TrackCountPerArtist RecordBuilder
   */
  public static at.technikum.TrackCountPerArtist.Builder newBuilder() {
    return new at.technikum.TrackCountPerArtist.Builder();
  }

  /**
   * Creates a new TrackCountPerArtist RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TrackCountPerArtist RecordBuilder
   */
  public static at.technikum.TrackCountPerArtist.Builder newBuilder(at.technikum.TrackCountPerArtist.Builder other) {
    if (other == null) {
      return new at.technikum.TrackCountPerArtist.Builder();
    } else {
      return new at.technikum.TrackCountPerArtist.Builder(other);
    }
  }

  /**
   * Creates a new TrackCountPerArtist RecordBuilder by copying an existing TrackCountPerArtist instance.
   * @param other The existing instance to copy.
   * @return A new TrackCountPerArtist RecordBuilder
   */
  public static at.technikum.TrackCountPerArtist.Builder newBuilder(at.technikum.TrackCountPerArtist other) {
    if (other == null) {
      return new at.technikum.TrackCountPerArtist.Builder();
    } else {
      return new at.technikum.TrackCountPerArtist.Builder(other);
    }
  }

  /**
   * RecordBuilder for TrackCountPerArtist instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TrackCountPerArtist>
    implements org.apache.avro.data.RecordBuilder<TrackCountPerArtist> {

    private java.lang.String artist;
    private int count;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(at.technikum.TrackCountPerArtist.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.artist)) {
        this.artist = data().deepCopy(fields()[0].schema(), other.artist);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.count)) {
        this.count = data().deepCopy(fields()[1].schema(), other.count);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing TrackCountPerArtist instance
     * @param other The existing instance to copy.
     */
    private Builder(at.technikum.TrackCountPerArtist other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.artist)) {
        this.artist = data().deepCopy(fields()[0].schema(), other.artist);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.count)) {
        this.count = data().deepCopy(fields()[1].schema(), other.count);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'artist' field.
      * @return The value.
      */
    public java.lang.String getArtist() {
      return artist;
    }

    /**
      * Gets the value of the 'artist' field as an Optional<java.lang.String>.
      * @return The Optional&lt;value&gt;.
      */
    public Optional<java.lang.String> getOptionalArtist() {
      return Optional.<java.lang.String>ofNullable(artist);
    }

    /**
      * Sets the value of the 'artist' field.
      * @param value The value of 'artist'.
      * @return This builder.
      */
    public at.technikum.TrackCountPerArtist.Builder setArtist(java.lang.String value) {
      validate(fields()[0], value);
      this.artist = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'artist' field has been set.
      * @return True if the 'artist' field has been set, false otherwise.
      */
    public boolean hasArtist() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'artist' field.
      * @return This builder.
      */
    public at.technikum.TrackCountPerArtist.Builder clearArtist() {
      artist = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'count' field.
      * @return The value.
      */
    public int getCount() {
      return count;
    }

    /**
      * Gets the value of the 'count' field as an Optional<java.lang.Integer>.
      * @return The Optional&lt;value&gt;.
      */
    public Optional<java.lang.Integer> getOptionalCount() {
      return Optional.<java.lang.Integer>ofNullable(count);
    }

    /**
      * Sets the value of the 'count' field.
      * @param value The value of 'count'.
      * @return This builder.
      */
    public at.technikum.TrackCountPerArtist.Builder setCount(int value) {
      validate(fields()[1], value);
      this.count = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'count' field has been set.
      * @return True if the 'count' field has been set, false otherwise.
      */
    public boolean hasCount() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'count' field.
      * @return This builder.
      */
    public at.technikum.TrackCountPerArtist.Builder clearCount() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TrackCountPerArtist build() {
      try {
        TrackCountPerArtist record = new TrackCountPerArtist();
        record.artist = fieldSetFlags()[0] ? this.artist : (java.lang.String) defaultValue(fields()[0]);
        record.count = fieldSetFlags()[1] ? this.count : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TrackCountPerArtist>
    WRITER$ = (org.apache.avro.io.DatumWriter<TrackCountPerArtist>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TrackCountPerArtist>
    READER$ = (org.apache.avro.io.DatumReader<TrackCountPerArtist>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.artist);

    out.writeInt(this.count);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.artist = in.readString();

      this.count = in.readInt();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.artist = in.readString();
          break;

        case 1:
          this.count = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










