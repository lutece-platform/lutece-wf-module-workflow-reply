/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.reply.util.constants;

/**
 *
 * ReplyConstants
 *
 */
public final class ReplyConstants
{
    // CONSTANTS
    public static final String UNDERSCORE = "_";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_AUTO = "auto";
    public static final String SLASH = "/";

    // BEANS
    public static final String BEAN_REPLY_CONFIG_SERVICE = "workflow-reply.taskReplyConfigService";

    // PROPERTIES
    public static final String PROPERTY_LABEL_STATE_AFTER_EDITION = "module.workflow.reply.task_reply_config.label_state_after_edition";
    public static final String PROPERTY_XPAGE_REPLY_PAGETITLE = "module.workflow.reply.reply.page_title";
    public static final String PROPERTY_XPAGE_REPLY_PATHLABEL = "module.workflow.reply.reply.page_label";
    public static final String PROPERTY_URL_RETURN = "workflow-reply.url_return";
    public static final String PROPERTY_LUTECE_BASE_URL = "lutece.base.url";
    public static final String PROPERTY_LUTECE_PROD_URL = "lutece.prod.url";

    // MARKS
    public static final String MARK_CONFIG = "config";
    public static final String MARK_LIST_STATES = "list_states";
    public static final String MARK_LIST_ENTRIES = "list_entries";
    public static final String MARK_REPLY = "reply";
    public static final String MARK_MAP_ID_ENTRY_LIST_RECORD_FIELD = "map_id_entry_list_record_field";
    public static final String MARK_WEBAPP_URL = "webapp_url";
    public static final String MARK_LOCALE = "locale";
    public static final String MARK_URL_RETURN = "url_return";
    public static final String MARK_SIGNATURE = "signature";
    public static final String MARK_TIMESTAMP = "timestamp";
    public static final String MARK_ID_DIRECTORY_RECORD = "id_directory_record";

    // PARAMETERS
    public static final String PARAMETER_REPLY_MESSAGE = "reply_message";
    public static final String PARAMETER_IDS_ENTRY = "ids_entry";
    public static final String PARAMETER_ID_HISTORY = "id_history";
    public static final String PARAMETER_ID_STATE = "id_state";
    public static final String PARAMETER_ID_TASK = "id_task";
    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_URL_RETURN = "url_return";
    public static final String PARAMETER_SIGNATURE = "signature";
    public static final String PARAMETER_TIMESTAMP = "timestamp";
    public static final String PARAMETER_DEFAULT_MESSAGE = "default_message";

    // TAGS
    public static final String TAG_REPLY = "reply";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_LIST_IDS_ENTRY = "list-ids-entry";
    public static final String TAG_ID_ENTRY = "id-entry";

    // SESSION
    public static final String SESSION_REPLY_LIST_SUBMITTED_RECORD_FIELDS = "reply_list_submitted_record_fields";

    // MESSAGES
    public static final String MESSAGE_DIRECTORY_ERROR = "module.workflow.reply.message.directory_error";
    public static final String MESSAGE_APP_ERROR = "module.workflow.reply.message.app_error";

    /**
     * Private constructor
     */
    private ReplyConstants( )
    {
    }
}
