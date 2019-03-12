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
package fr.paris.lutece.plugins.workflow.modules.reply.service.task;

import fr.paris.lutece.plugins.workflow.modules.reply.business.task.Reply;
import fr.paris.lutece.plugins.workflow.modules.reply.service.IReplyService;
import fr.paris.lutece.plugins.workflow.modules.reply.util.constants.ReplyConstants;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.Task;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.i18n.I18nService;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * This class represents a task to reply to a ticket
 *
 */
public class TaskReply extends Task
{
    private static final String MESSAGE_REPLY = "module.workflow.reply.task_reply.labelReply";

    // PARAMETERS
    public static final String PARAMETER_USER_MESSAGE = "user_message";

    // Variables
    private AdminUser _user;

    @Inject
    private IReplyService _replyService;
    @Inject
    @Named( ReplyConstants.BEAN_REPLY_CONFIG_SERVICE )
    private ITaskConfigService _taskReplyConfigService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( MESSAGE_REPLY, locale );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init( )
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        String strMessage = request.getParameter( ReplyConstants.PARAMETER_REPLY_MESSAGE + ReplyConstants.UNDERSCORE + getId( ) );

        boolean bCreate = false;

        Reply reply = _replyService.find( nIdResourceHistory, getId( ) );

        if ( reply == null )
        {
            reply = new Reply( );
            reply.setIdHistory( nIdResourceHistory );
            reply.setIdTask( getId( ) );
            bCreate = true;
        }

        reply.setMessage( StringUtils.isNotBlank( strMessage ) ? strMessage : StringUtils.EMPTY );

        if ( bCreate )
        {
            _replyService.create( reply );
        }
        else
        {
            _replyService.update( reply );
        }
    }

    // GET

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getTaskFormEntries( Locale locale )
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveConfig( )
    {
        _replyService.removeByIdTask( getId( ) );
        _taskReplyConfigService.remove( getId( ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveTaskInformation( int nIdHistory )
    {
        _replyService.removeByIdHistory( nIdHistory, getId( ) );
    }
}
