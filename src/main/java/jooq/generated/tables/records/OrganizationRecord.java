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
public class OrganizationRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.OrganizationRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -1457852817;

	/**
	 * Setter for <code>tutorme.organization.orgTitle</code>.
	 */
	public void setOrgtitle(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>tutorme.organization.orgTitle</code>.
	 */
	public java.lang.String getOrgtitle() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>tutorme.organization.description</code>.
	 */
	public void setDescription(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>tutorme.organization.description</code>.
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(1);
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
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return jooq.generated.tables.Organization.ORGANIZATION.ORGTITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return jooq.generated.tables.Organization.ORGANIZATION.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getOrgtitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationRecord value1(java.lang.String value) {
		setOrgtitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationRecord value2(java.lang.String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationRecord values(java.lang.String value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OrganizationRecord
	 */
	public OrganizationRecord() {
		super(jooq.generated.tables.Organization.ORGANIZATION);
	}

	/**
	 * Create a detached, initialised OrganizationRecord
	 */
	public OrganizationRecord(java.lang.String orgtitle, java.lang.String description) {
		super(jooq.generated.tables.Organization.ORGANIZATION);

		setValue(0, orgtitle);
		setValue(1, description);
	}
}