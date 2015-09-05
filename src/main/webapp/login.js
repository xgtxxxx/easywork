Ext.onReady(function() {
	var login = Ext.create('Ext.form.Panel',{
		border : false,
		bodyPadding : 20,
		defaults : {
			labelAlign : 'right'
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : 'Email',
			name : 'email',
			vtype : 'email',
			allowBlank : false,
			anchor : '100%'
		}, {
			xtype : 'textfield',
			fieldLabel : 'Password',
			inputType : 'password',
			name : 'password',
			allowBlank : false,
			anchor : '100%'
		} ],
		buttonAlign : 'center',
		buttons : [{
			text : 'Submit',
			handler : function() {
				var isValid = login.getForm().isValid();
				if (isValid) {
					login.getForm().submit({
						url : CTX.PATH+ '/login.do',
						success : function(form,action) {
							if (action.result.success) {
								window.location.href = CTX.PATH+ '/home.htm';
							} else {
								Ext.Msg.alert('Failure',action.result.message);
							}
						},
						failure : function(form,action) {
							switch (action.failureType) {
							case Ext.form.action.Action.CLIENT_INVALID:
								Ext.Msg.alert('Failure','Form fields may not be submitted with invalid values');
								break;
							case Ext.form.action.Action.CONNECT_FAILURE:
								Ext.Msg.alert('Failure','Ajax communication failed');
								break;
							case Ext.form.action.Action.SERVER_INVALID:
								Ext.Msg.alert('Failure',action.result.message);
							}
						}
					});
				}
			}
		}, {
			text : 'Clear',
			handler : function() {
				login.getForm().reset();
			}
		} ]
	});

	var win = Ext.create('Ext.window.Window', {
		title : 'Login',
		width : 400,
		height : 200,
		modal : true,
		layout : 'fit',
		draggable : false,
		closable : false,
		items : [ login ]
	});

	win.show();
});
