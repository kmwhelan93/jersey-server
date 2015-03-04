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
public class PortalsRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.PortalsRecord> implements org.jooq.Record6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long> {

	private static final long serialVersionUID = 861247265;

	/**
	 * Setter for <code>maimon.portals.portal_id</code>.
	 */
	public void setPortalId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>maimon.portals.portal_id</code>.
	 */
	public java.lang.Integer getPortalId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>maimon.portals.username</code>.
	 */
	public void setUsername(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>maimon.portals.username</code>.
	 */
	public java.lang.String getUsername() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>maimon.portals.base_id1</code>.
	 */
	public void setBaseId1(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>maimon.portals.base_id1</code>.
	 */
	public java.lang.Integer getBaseId1() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>maimon.portals.base_id2</code>.
	 */
	public void setBaseId2(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>maimon.portals.base_id2</code>.
	 */
	public java.lang.Integer getBaseId2() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>maimon.portals.flow_rate</code>.
	 */
	public void setFlowRate(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>maimon.portals.flow_rate</code>.
	 */
	public java.lang.Integer getFlowRate() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>maimon.portals.time_finished</code>.
	 */
	public void setTimeFinished(java.lang.Long value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>maimon.portals.time_finished</code>.
	 */
	public java.lang.Long getTimeFinished() {
		return (java.lang.Long) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Long> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return jooq.generated.tables.Portals.PORTALS.PORTAL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return jooq.generated.tables.Portals.PORTALS.USERNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return jooq.generated.tables.Portals.PORTALS.BASE_ID1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return jooq.generated.tables.Portals.PORTALS.BASE_ID2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return jooq.generated.tables.Portals.PORTALS.FLOW_RATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field6() {
		return jooq.generated.tables.Portals.PORTALS.TIME_FINISHED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getPortalId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getUsername();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getBaseId1();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getBaseId2();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getFlowRate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value6() {
		return getTimeFinished();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value1(java.lang.Integer value) {
		setPortalId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value2(java.lang.String value) {
		setUsername(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value3(java.lang.Integer value) {
		setBaseId1(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value4(java.lang.Integer value) {
		setBaseId2(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value5(java.lang.Integer value) {
		setFlowRate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord value6(java.lang.Long value) {
		setTimeFinished(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortalsRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.Integer value4, java.lang.Integer value5, java.lang.Long value6) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PortalsRecord
	 */
	public PortalsRecord() {
		super(jooq.generated.tables.Portals.PORTALS);
	}

	/**
	 * Create a detached, initialised PortalsRecord
	 */
	public PortalsRecord(java.lang.Integer portalId, java.lang.String username, java.lang.Integer baseId1, java.lang.Integer baseId2, java.lang.Integer flowRate, java.lang.Long timeFinished) {
		super(jooq.generated.tables.Portals.PORTALS);

		setValue(0, portalId);
		setValue(1, username);
		setValue(2, baseId1);
		setValue(3, baseId2);
		setValue(4, flowRate);
		setValue(5, timeFinished);
	}
}
