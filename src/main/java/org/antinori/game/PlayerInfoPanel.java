package org.antinori.game;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PlayerInfoPanel extends JPanel {

	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField10;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField13;
	private javax.swing.JTextField jTextField14;
	private javax.swing.JTextField jTextField15;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;

	static Font FONT = new java.awt.Font("Berlin Sans FB Demi", 0, 14);
	private org.jdesktop.beansbinding.BindingGroup bindingGroup;
	private Player player1;

	public PlayerInfoPanel() {
	}

	public void setPlayer(Player player) {
		this.player1 = player;
		initComponents();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initComponents();
			}
		});
	}

	private void initComponents() {
		bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jTextField7 = new javax.swing.JTextField();
		jTextField8 = new javax.swing.JTextField();
		jTextField9 = new javax.swing.JTextField();
		jTextField10 = new javax.swing.JTextField();
		jTextField11 = new javax.swing.JTextField();
		jTextField12 = new javax.swing.JTextField();
		jTextField13 = new javax.swing.JTextField();
		jTextField14 = new javax.swing.JTextField();
		jTextField15 = new javax.swing.JTextField();

		jTextField1.setFont(FONT); // NOI18N
		jTextField2.setFont(FONT); // NOI18N
		jTextField3.setFont(FONT); // NOI18N
		jTextField4.setFont(FONT); // NOI18N
		jTextField5.setFont(FONT); // NOI18N
		jTextField6.setFont(FONT); // NOI18N
		jTextField7.setFont(FONT); // NOI18N
		jTextField8.setFont(FONT); // NOI18N
		jTextField9.setFont(FONT); // NOI18N
		jTextField10.setFont(FONT); // NOI18N
		jTextField11.setFont(FONT); // NOI18N
		jTextField12.setFont(FONT); // NOI18N
		jTextField13.setFont(FONT); // NOI18N
		jTextField14.setFont(FONT); // NOI18N
		jTextField15.setFont(FONT); // NOI18N


		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setEditable(false);
		jTextField4.setEditable(false);
		jTextField5.setEditable(false);
		jTextField6.setEditable(false);
		jTextField7.setEditable(false);
		jTextField8.setEditable(false);
		jTextField9.setEditable(false);
		jTextField10.setEditable(false);
		jTextField11.setEditable(false);
		jTextField12.setEditable(false);
		jTextField13.setEditable(false);
		jTextField14.setEditable(false);
		jTextField15.setEditable(false);

		org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${playerColor}"), this,
				org.jdesktop.beansbinding.BeanProperty.create("background"));
		bindingGroup.addBinding(binding);

		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${playerName}"), jTextField1,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("Current Savings $${money}"), jTextField2,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${married_status_text} ${child_count} Children"), jTextField3,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${loans} Loans"), jTextField4,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${life_card_count} LIFE cards"), jTextField5,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${lti_card_value} LTI Value"), jTextField6,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_1}"), jTextField7,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_2}"), jTextField8,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_3}"), jTextField9,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${career_name}"), jTextField10,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("Current Salary $${salary}"), jTextField11,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${house_name}"), jTextField12,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_4}"), jTextField13,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_5}"), jTextField14,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);
		binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, player1, org.jdesktop.beansbinding.ELProperty.create("${stw_card_6}"), jTextField15,
				org.jdesktop.beansbinding.BeanProperty.create("text"));
		bindingGroup.addBinding(binding);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
										.addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jTextField1).addComponent(jTextField2).addComponent(jTextField3))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextField4).addComponent(jTextField5).addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addComponent(jTextField12))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextField7).addComponent(jTextField8).addComponent(jTextField9).addComponent(jTextField13).addComponent(jTextField14)
										.addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				layout.createSequentialGroup().addGap(32, 32, 32)
																						.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				layout.createSequentialGroup()
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		bindingGroup.bind();
	}

}
