Ext.define('app.view.job.task.TriggerForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.triggerform',
	border : false,
	fieldDefaults : {
		labelAlign : "right",
		width : 300
	},
	items : [ {
		xtype : 'radiogroup',
		fieldLabel : 'Job Type',
		layout : 'hbox',
		defaults : {
			flex : 1
		},
		items : [ {
			boxLabel : 'Simple',
			name : 'type',
			inputValue : 'SimpleTrigger',
			checked : true
		}, {
			boxLabel : 'Cron',
			name : 'type',
			inputValue : 'CronTrigger'
		} ],
		listeners : {
			change : function(radio, newValue, oldValue, eOpts) {
				if(newValue.type=='SimpleTrigger'){
					this.ownerCt.items.items[3].setHidden(true);
					this.ownerCt.items.items[4].setHidden(false);
					this.ownerCt.items.items[5].setHidden(false);
					this.ownerCt.items.items[6].setHidden(false);
				}else{
					this.ownerCt.items.items[3].setHidden(false);
					this.ownerCt.items.items[4].setHidden(true);
					this.ownerCt.items.items[5].setHidden(true);
					this.ownerCt.items.items[6].setHidden(true);
				}
			}
		}
	}, {
		xtype : 'textfield',
		fieldLabel : 'Name',
		name : 'name'
	}, {
		xtype : 'textfield',
		fieldLabel : 'Description',
		name : 'description'
	}, {
		xtype : 'textfield',
		fieldLabel : 'Cron Expressions',
		hidden : true,
		name : 'cron'
	}, {
		xtype : 'textfield',
		fieldLabel : 'Run Time',
		name : 'runTime'
	}, {
		xtype : 'numberfield',
		fieldLabel : 'Execute Count',
		name : 'repectCount',
		value : 1
	}, {
		layout : 'column',
		border : false,
		items : [ {
			xtype : 'numberfield',
			fieldLabel : 'Repect Interval',
			name : 'repectInterval',
			width : 300,
			value : 0
		}, {
			width : 50,
			name : 'unit',
			xtype : 'combo',
			queryMode : 'local',
			store : [ 'S', 'M', 'H', 'D' ],
			value : 'S'
		} ]
	}, {
		xtype : 'hidden',
		name : 'jobName'
	} ],
	setJobName : function(jobName){
		this.down('hidden').setValue(jobName);
	}
});