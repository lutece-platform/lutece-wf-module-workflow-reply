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
package fr.paris.lutece.plugins.workflow.modules.reply.service;

import fr.paris.lutece.plugins.workflow.modules.reply.business.task.IReplyDAO;
import fr.paris.lutece.plugins.workflow.modules.reply.business.task.Reply;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.state.IStateService;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.message.SiteMessageService;
import fr.paris.lutece.portal.service.plugin.PluginService;

import org.apache.commons.lang.StringUtils;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * ReplyService
 *
 */
public class ReplyService implements IReplyService
{
    public static final String BEAN_SERVICE = "workflow-reply.replyService";

    // SERVICES
    @Inject
    private IStateService _stateService;
    @Inject
    private IActionService _actionService;

    // DAO
    @Inject
    private IReplyDAO _replyDAO;

    // SET

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSiteMessage( HttpServletRequest request, String strMessage, int nTypeMessage, String strUrlReturn ) throws SiteMessageException
    {
        if ( StringUtils.isNotBlank( strUrlReturn ) )
        {
            SiteMessageService.setMessage( request, strMessage, nTypeMessage, strUrlReturn );
        }
        else
        {
            SiteMessageService.setMessage( request, strMessage, nTypeMessage );
        }
    }

    // CRUD

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    public void create( Reply reply )
    {
        if ( reply != null )
        {
            _replyDAO.insert( reply, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    public void update( Reply reply )
    {
        if ( reply != null )
        {
            _replyDAO.store( reply, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reply find( int nIdHistory, int nIdTask )
    {
        Reply reply = _replyDAO.load( nIdHistory, nIdTask, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );

        return reply;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reply> findByIdTask( int nIdTask )
    {
        return _replyDAO.loadByIdTask( nIdTask, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    public void removeByIdHistory( int nIdHistory, int nIdTask )
    {
        Reply reply = find( nIdHistory, nIdTask );

        if ( reply != null )
        {
            _replyDAO.deleteByIdHistory( nIdHistory, nIdTask, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( ReplyPlugin.BEAN_TRANSACTION_MANAGER )
    public void removeByIdTask( int nIdTask )
    {
        _replyDAO.deleteByIdTask( nIdTask, PluginService.getPlugin( ReplyPlugin.PLUGIN_NAME ) );
    }

}
