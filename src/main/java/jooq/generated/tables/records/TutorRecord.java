/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TutorRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.TutorRecord> implements org.jooq.Record8<java.lang.String, java.lang.Integer, java.lang.String, java.math.BigDecimal, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -110598059;

	/**
	 * Setter for <code>tutorme.tutor.compID</code>.
	 */
	public void setCompid(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.compID</code>.
	 */
	public java.lang.String getCompid() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>tutorme.tutor.year</code>.
	 */
	public void setYear(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.year</code>.
	 */
	public java.lang.Integer getYear() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>tutorme.tutor.bio</code>.
	 */
	public void setBio(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.bio</code>.
	 */
	public java.lang.String getBio() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>tutorme.tutor.gpa</code>.
	 */
	public void setGpa(java.math.BigDecimal value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.gpa</code>.
	 */
	public java.math.BigDecimal getGpa() {
		return (java.math.BigDecimal) getValue(3);
	}

	/**
	 * Setter for <code>tutorme.tutor.isActive</code>.
	 */
	public void setIsactive(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.isActive</code>.
	 */
	public java.lang.Integer getIsactive() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>tutorme.tutor.hourlyRate</code>.
	 */
	public void setHourlyrate(java.math.BigDecimal value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.hourlyRate</code>.
	 */
	public java.math.BigDecimal getHourlyrate() {
		return (java.math.BigDecimal) getValue(5);
	}

	/**
	 * Setter for <code>tutorme.tutor.photoURL</code>.
	 */
	public void setPhotourl(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.photoURL</code>.
	 */
	public java.lang.String getPhotourl() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>tutorme.tutor.major</code>.
	 */
	public void setMajor(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>tutorme.tutor.major</code>.
	 */
	public java.lang.String getMajor() {
		return (java.lang.String) getValue(7);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.String, java.lang.Integer, java.lang.String, java.math.BigDecimal, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.String, java.lang.Integer, java.lang.String, java.math.BigDecimal, java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return jooq.generated.tables.Tutor.TUTOR.COMPID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return jooq.generated.tables.Tutor.TUTOR.YEAR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return jooq.generated.tables.Tutor.TUTOR.BIO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field4() {
		return jooq.generated.tables.Tutor.TUTOR.GPA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return jooq.generated.tables.Tutor.TUTOR.ISACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field6() {
		return jooq.generated.tables.Tutor.TUTOR.HOURLYRATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return jooq.generated.tables.Tutor.TUTOR.PHOTOURL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return jooq.generated.tables.Tutor.TUTOR.MAJOR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getCompid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getYear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getBio();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value4() {
		return getGpa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getIsactive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value6() {
		return getHourlyrate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getPhotourl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getMajor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value1(java.lang.String value) {
		setCompid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value2(java.lang.Integer value) {
		setYear(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value3(java.lang.String value) {
		setBio(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value4(java.math.BigDecimal value) {
		setGpa(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value5(java.lang.Integer value) {
		setIsactive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value6(java.math.BigDecimal value) {
		setHourlyrate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value7(java.lang.String value) {
		setPhotourl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord value8(java.lang.String value) {
		setMajor(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TutorRecord values(java.lang.String value1, java.lang.Integer value2, java.lang.String value3, java.math.BigDecimal value4, java.lang.Integer value5, java.math.BigDecimal value6, java.lang.String value7, java.lang.String value8) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TutorRecord
	 */
	public TutorRecord() {
		super(jooq.generated.tables.Tutor.TUTOR);
	}

	/**
	 * Create a detached, initialised TutorRecord
	 */
	public TutorRecord(java.lang.String compid, java.lang.Integer year, java.lang.String bio, java.math.BigDecimal gpa, java.lang.Integer isactive, java.math.BigDecimal hourlyrate, java.lang.String photourl, java.lang.String major) {
		super(jooq.generated.tables.Tutor.TUTOR);

		setValue(0, compid);
		setValue(1, year);
		setValue(2, bio);
		setValue(3, gpa);
		setValue(4, isactive);
		setValue(5, hourlyrate);
		setValue(6, photourl);
		setValue(7, major);
	}
}
