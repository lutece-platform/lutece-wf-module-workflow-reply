/*
 * Copyright (c) 2002-2019, Mairie de Paris
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.reply.web.task;

import fr.paris.lutece.plugins.workflow.modules.reply.business.config.TaskReplyConfig;
import fr.paris.lutece.plugins.workflow.modules.reply.business.task.Reply;
import fr.paris.lutece.plugins.workflow.modules.reply.service.IReplyService;
import fr.paris.lutece.plugins.workflow.modules.reply.service.ReplyService;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.AbstractTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.util.html.HtmlTemplate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author leridons
 */
public class TaskReplyComponent extends AbstractTaskComponent
{

    // TEMPLATES
    private static final String TEMPLATE_TASK_REPLY_CONFIG = "admin/plugins/workflow/modules/reply/task_reply_config.html";
    private static final String TEMPLATE_TASK_REPLY_FORM = "admin/plugins/workflow/modules/reply/task_reply_form.html";

    // MARKERS
    private static final String MARK_CONFIG = "config";
    private static final String MARK_WEBAPP_URL = "webapp_url";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_MESSAGE = "message";

    // PARAMETERS
    private static final String PARAMETER_REPLY_VALUE = "reply_message";

    // MESSAGES
    private static final String MESSAGE_MANDATORY_FIELD = "module.workflow.reply.task_reply_config.message.mandatory.field";
    private static final String MESSAGE_NO_CONFIGURATION_FOR_TASK_REPLY = "module.workflow.reply.task_reply_config.message.no_configuration_for_task_reply";

    @Override
    public String getDisplayTaskForm( int nIdResource, String strResourceType, HttpServletRequest request, Locale locale, ITask task )
    {
        Map<String, Object> model = new HashMap<>( );

        TaskReplyConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );

        String strMessage = request.getParameter( PARAMETER_REPLY_VALUE + "_" + task.getId( ) );

        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        model.put( MARK_LOCALE, AdminUserService.getLocale( request ).getLanguage( ) );
        model.put( MARK_MESSAGE, strMessage );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_REPLY_FORM, locale, model );

        return template.getHtml( );
    }

    @Override
    public String getDisplayConfigForm( HttpServletRequest request, Locale locale, ITask task )
    {
        Map<String, Object> model = new HashMap<>( );

        TaskReplyConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_REPLY_CONFIG, locale, model );

        return template.getHtml( );
    }

    @Override
    public String getDisplayTaskInformation( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        TaskReplyConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );

        String strInfo = "";
        if ( config != null )
            strInfo = config.getTitle( );

        IReplyService replyService = SpringContextService.getBean( ReplyService.BEAN_SERVICE );
        Reply reply = replyService.find( nIdHistory, task.getId( ) );

        if ( reply != null )
            strInfo += " : " + reply.getMessage( );

        return strInfo;
    }

    @Override
    public String getTaskInformationXml( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        throw new UnsupportedOperationException( "Not supported yet." ); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String doValidateTask( int nIdResource, String strResourceType, HttpServletRequest request, Locale locale, ITask task )
    {
        String strError = WorkflowUtils.EMPTY_STRING;
        String strReplyMessageValue = request.getParameter( PARAMETER_REPLY_VALUE + "_" + task.getId( ) );
        TaskReplyConfig config = this.getTaskConfigService( ).findByPrimaryKey( task.getId( ) );

        if ( config == null )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_NO_CONFIGURATION_FOR_TASK_REPLY, AdminMessage.TYPE_STOP );
        }

        if ( config.isMandatory( ) && ( ( strReplyMessageValue == null ) || strReplyMessageValue.trim( ).equals( WorkflowUtils.EMPTY_STRING ) ) )
        {
            strError = config.getTitle( );
        }

        if ( StringUtils.isNotBlank( strError ) )
        {
            Object [ ] tabRequiredFields = {
                strError
            };

            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELD, tabRequiredFields, AdminMessage.TYPE_STOP );
        }

        return null;
    }

}
