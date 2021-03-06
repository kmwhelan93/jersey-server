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
public class AttacksRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.AttacksRecord> implements org.jooq.Record11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer> {

	private static final long serialVersionUID = -1283326939;

	/**
	 * Setter for <code>maimon.attacks.attackId</code>.
	 */
	public void setAttackid(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>maimon.attacks.attackId</code>.
	 */
	public java.lang.Integer getAttackid() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>maimon.attacks.attacker</code>.
	 */
	public void setAttacker(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>maimon.attacks.attacker</code>.
	 */
	public java.lang.String getAttacker() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>maimon.attacks.attacker_base_id</code>.
	 */
	public void setAttackerBaseId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>maimon.attacks.attacker_base_id</code>.
	 */
	public java.lang.Integer getAttackerBaseId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>maimon.attacks.attacker_wormhole_id</code>.
	 */
	public void setAttackerWormholeId(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>maimon.attacks.attacker_wormhole_id</code>.
	 */
	public java.lang.Integer getAttackerWormholeId() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>maimon.attacks.defender</code>.
	 */
	public void setDefender(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>maimon.attacks.defender</code>.
	 */
	public java.lang.String getDefender() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>maimon.attacks.defender_base_id</code>.
	 */
	public void setDefenderBaseId(java.lang.Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>maimon.attacks.defender_base_id</code>.
	 */
	public java.lang.Integer getDefenderBaseId() {
		return (java.lang.Integer) getValue(5);
	}

	/**
	 * Setter for <code>maimon.attacks.defender_wormhole_id</code>.
	 */
	public void setDefenderWormholeId(java.lang.Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>maimon.attacks.defender_wormhole_id</code>.
	 */
	public java.lang.Integer getDefenderWormholeId() {
		return (java.lang.Integer) getValue(6);
	}

	/**
	 * Setter for <code>maimon.attacks.time_iniated</code>.
	 */
	public void setTimeIniated(java.lang.Long value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>maimon.attacks.time_iniated</code>.
	 */
	public java.lang.Long getTimeIniated() {
		return (java.lang.Long) getValue(7);
	}

	/**
	 * Setter for <code>maimon.attacks.time_attack_lands</code>.
	 */
	public void setTimeAttackLands(java.lang.Long value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>maimon.attacks.time_attack_lands</code>.
	 */
	public java.lang.Long getTimeAttackLands() {
		return (java.lang.Long) getValue(8);
	}

	/**
	 * Setter for <code>maimon.attacks.last_update</code>.
	 */
	public void setLastUpdate(java.lang.Long value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>maimon.attacks.last_update</code>.
	 */
	public java.lang.Long getLastUpdate() {
		return (java.lang.Long) getValue(9);
	}

	/**
	 * Setter for <code>maimon.attacks.num_units</code>.
	 */
	public void setNumUnits(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>maimon.attacks.num_units</code>.
	 */
	public java.lang.Integer getNumUnits() {
		return (java.lang.Integer) getValue(10);
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
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer> valuesRow() {
		return (org.jooq.Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return jooq.generated.tables.Attacks.ATTACKS.ATTACKID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return jooq.generated.tables.Attacks.ATTACKS.ATTACKER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return jooq.generated.tables.Attacks.ATTACKS.ATTACKER_BASE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return jooq.generated.tables.Attacks.ATTACKS.ATTACKER_WORMHOLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return jooq.generated.tables.Attacks.ATTACKS.DEFENDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field6() {
		return jooq.generated.tables.Attacks.ATTACKS.DEFENDER_BASE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field7() {
		return jooq.generated.tables.Attacks.ATTACKS.DEFENDER_WORMHOLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field8() {
		return jooq.generated.tables.Attacks.ATTACKS.TIME_INIATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field9() {
		return jooq.generated.tables.Attacks.ATTACKS.TIME_ATTACK_LANDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field10() {
		return jooq.generated.tables.Attacks.ATTACKS.LAST_UPDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return jooq.generated.tables.Attacks.ATTACKS.NUM_UNITS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getAttackid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getAttacker();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getAttackerBaseId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getAttackerWormholeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getDefender();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value6() {
		return getDefenderBaseId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value7() {
		return getDefenderWormholeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value8() {
		return getTimeIniated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value9() {
		return getTimeAttackLands();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value10() {
		return getLastUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getNumUnits();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value1(java.lang.Integer value) {
		setAttackid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value2(java.lang.String value) {
		setAttacker(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value3(java.lang.Integer value) {
		setAttackerBaseId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value4(java.lang.Integer value) {
		setAttackerWormholeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value5(java.lang.String value) {
		setDefender(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value6(java.lang.Integer value) {
		setDefenderBaseId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value7(java.lang.Integer value) {
		setDefenderWormholeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value8(java.lang.Long value) {
		setTimeIniated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value9(java.lang.Long value) {
		setTimeAttackLands(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value10(java.lang.Long value) {
		setLastUpdate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord value11(java.lang.Integer value) {
		setNumUnits(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttacksRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.Integer value4, java.lang.String value5, java.lang.Integer value6, java.lang.Integer value7, java.lang.Long value8, java.lang.Long value9, java.lang.Long value10, java.lang.Integer value11) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AttacksRecord
	 */
	public AttacksRecord() {
		super(jooq.generated.tables.Attacks.ATTACKS);
	}

	/**
	 * Create a detached, initialised AttacksRecord
	 */
	public AttacksRecord(java.lang.Integer attackid, java.lang.String attacker, java.lang.Integer attackerBaseId, java.lang.Integer attackerWormholeId, java.lang.String defender, java.lang.Integer defenderBaseId, java.lang.Integer defenderWormholeId, java.lang.Long timeIniated, java.lang.Long timeAttackLands, java.lang.Long lastUpdate, java.lang.Integer numUnits) {
		super(jooq.generated.tables.Attacks.ATTACKS);

		setValue(0, attackid);
		setValue(1, attacker);
		setValue(2, attackerBaseId);
		setValue(3, attackerWormholeId);
		setValue(4, defender);
		setValue(5, defenderBaseId);
		setValue(6, defenderWormholeId);
		setValue(7, timeIniated);
		setValue(8, timeAttackLands);
		setValue(9, lastUpdate);
		setValue(10, numUnits);
	}
}
