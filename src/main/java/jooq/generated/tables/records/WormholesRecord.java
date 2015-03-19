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
public class WormholesRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.WormholesRecord> implements org.jooq.Record6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -84447173;

	/**
	 * Setter for <code>maimon.wormholes.wormhole_id</code>.
	 */
	public void setWormholeId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.wormhole_id</code>.
	 */
	public java.lang.Integer getWormholeId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>maimon.wormholes.base_id</code>.
	 */
	public void setBaseId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.base_id</code>.
	 */
	public java.lang.Integer getBaseId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>maimon.wormholes.owner</code>.
	 */
	public void setOwner(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.owner</code>.
	 */
	public java.lang.String getOwner() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>maimon.wormholes.relative_coord_x</code>.
	 */
	public void setRelativeCoordX(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.relative_coord_x</code>.
	 */
	public java.lang.Integer getRelativeCoordX() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>maimon.wormholes.relative_coord_y</code>.
	 */
	public void setRelativeCoordY(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.relative_coord_y</code>.
	 */
	public java.lang.Integer getRelativeCoordY() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>maimon.wormholes.connected_wormhole_id</code>.
	 */
	public void setConnectedWormholeId(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>maimon.wormholes.connected_wormhole_id</code>.
	 */
	public java.lang.Integer getConnectedWormholeId() {
		return (java.lang.Integer) getValue(5);
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
	public org.jooq.Row6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return jooq.generated.tables.Wormholes.WORMHOLES.WORMHOLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return jooq.generated.tables.Wormholes.WORMHOLES.BASE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return jooq.generated.tables.Wormholes.WORMHOLES.OWNER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return jooq.generated.tables.Wormholes.WORMHOLES.RELATIVE_COORD_X;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return jooq.generated.tables.Wormholes.WORMHOLES.RELATIVE_COORD_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return jooq.generated.tables.Wormholes.WORMHOLES.CONNECTED_WORMHOLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getWormholeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getBaseId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getOwner();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getRelativeCoordX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getRelativeCoordY();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getConnectedWormholeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value1(java.lang.Integer value) {
		setWormholeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value2(java.lang.Integer value) {
		setBaseId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value3(java.lang.String value) {
		setOwner(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value4(java.lang.Integer value) {
		setRelativeCoordX(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value5(java.lang.Integer value) {
		setRelativeCoordY(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord value6(java.lang.Integer value) {
		setConnectedWormholeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WormholesRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.Integer value4, java.lang.Integer value5, java.lang.Integer value6) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached WormholesRecord
	 */
	public WormholesRecord() {
		super(jooq.generated.tables.Wormholes.WORMHOLES);
	}

	/**
	 * Create a detached, initialised WormholesRecord
	 */
	public WormholesRecord(java.lang.Integer wormholeId, java.lang.Integer baseId, java.lang.String owner, java.lang.Integer relativeCoordX, java.lang.Integer relativeCoordY, java.lang.Integer connectedWormholeId) {
		super(jooq.generated.tables.Wormholes.WORMHOLES);

		setValue(0, wormholeId);
		setValue(1, baseId);
		setValue(2, owner);
		setValue(3, relativeCoordX);
		setValue(4, relativeCoordY);
		setValue(5, connectedWormholeId);
	}
}
