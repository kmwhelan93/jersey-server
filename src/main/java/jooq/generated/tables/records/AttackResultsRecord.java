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
public class AttackResultsRecord extends org.jooq.impl.UpdatableRecordImpl<jooq.generated.tables.records.AttackResultsRecord> implements org.jooq.Record6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.lang.Byte> {

	private static final long serialVersionUID = -1253337944;

	/**
	 * Setter for <code>maimon.attack_results.attack_id</code>.
	 */
	public void setAttackId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.attack_id</code>.
	 */
	public java.lang.Integer getAttackId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>maimon.attack_results.winner_username</code>.
	 */
	public void setWinnerUsername(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.winner_username</code>.
	 */
	public java.lang.String getWinnerUsername() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>maimon.attack_results.num_units_left</code>.
	 */
	public void setNumUnitsLeft(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.num_units_left</code>.
	 */
	public java.lang.Integer getNumUnitsLeft() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>maimon.attack_results.new_base_id</code>.
	 */
	public void setNewBaseId(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.new_base_id</code>.
	 */
	public java.lang.Integer getNewBaseId() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>maimon.attack_results.winner_has_viewed</code>.
	 */
	public void setWinnerHasViewed(java.lang.Byte value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.winner_has_viewed</code>.
	 */
	public java.lang.Byte getWinnerHasViewed() {
		return (java.lang.Byte) getValue(4);
	}

	/**
	 * Setter for <code>maimon.attack_results.loser_has_viewed</code>.
	 */
	public void setLoserHasViewed(java.lang.Byte value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>maimon.attack_results.loser_has_viewed</code>.
	 */
	public java.lang.Byte getLoserHasViewed() {
		return (java.lang.Byte) getValue(5);
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
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.lang.Byte> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Byte, java.lang.Byte> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.ATTACK_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.WINNER_USERNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.NUM_UNITS_LEFT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.NEW_BASE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field5() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.WINNER_HAS_VIEWED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field6() {
		return jooq.generated.tables.AttackResults.ATTACK_RESULTS.LOSER_HAS_VIEWED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getAttackId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getWinnerUsername();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getNumUnitsLeft();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getNewBaseId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value5() {
		return getWinnerHasViewed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value6() {
		return getLoserHasViewed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value1(java.lang.Integer value) {
		setAttackId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value2(java.lang.String value) {
		setWinnerUsername(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value3(java.lang.Integer value) {
		setNumUnitsLeft(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value4(java.lang.Integer value) {
		setNewBaseId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value5(java.lang.Byte value) {
		setWinnerHasViewed(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord value6(java.lang.Byte value) {
		setLoserHasViewed(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttackResultsRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.Integer value4, java.lang.Byte value5, java.lang.Byte value6) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AttackResultsRecord
	 */
	public AttackResultsRecord() {
		super(jooq.generated.tables.AttackResults.ATTACK_RESULTS);
	}

	/**
	 * Create a detached, initialised AttackResultsRecord
	 */
	public AttackResultsRecord(java.lang.Integer attackId, java.lang.String winnerUsername, java.lang.Integer numUnitsLeft, java.lang.Integer newBaseId, java.lang.Byte winnerHasViewed, java.lang.Byte loserHasViewed) {
		super(jooq.generated.tables.AttackResults.ATTACK_RESULTS);

		setValue(0, attackId);
		setValue(1, winnerUsername);
		setValue(2, numUnitsLeft);
		setValue(3, newBaseId);
		setValue(4, winnerHasViewed);
		setValue(5, loserHasViewed);
	}
}
