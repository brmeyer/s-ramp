/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.sramp.ui.client.services.growl;

import java.util.ArrayList;
import java.util.List;

import org.overlord.sramp.ui.client.animation.FadeOutAnimation;
import org.overlord.sramp.ui.client.animation.MoveAnimation;
import org.overlord.sramp.ui.client.services.AbstractService;
import org.overlord.sramp.ui.client.widgets.dialogs.GrowlDialog;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;

/**
 * Implements the lightweight notification service.
 *
 * @author eric.wittmann@redhat.com
 */
public class GrowlService extends AbstractService implements IGrowlService {

	private List<Growl> activeGrowls = new ArrayList<Growl>();
	private List<Growl> completedGrowls = new ArrayList<Growl>();
	private int growlCounter = 0;

	/**
	 * Constructor.
	 */
	public GrowlService() {
	}

	/**
	 * @see org.overlord.sramp.ui.client.services.growl.IGrowlService#growl(java.lang.String, java.lang.String)
	 */
	@Override
	public void growl(String title, String message) {
		final Growl growl = createGrowl(title, message);
		createGrowlDialog(growl, title, message);
		createGrowlTimers(growl);
		createGrowlAnimations(growl);

		positionAndShowGrowlDialog(growl);
		
		// Schedule the growl to go away automatically.
		growl.getAliveTimer().schedule(5000);
	}

	/**
	 * Called when a growl is closed, either by the user clicking on the close button
	 * or the alive timer fires.
	 * @param growl
	 */
	protected void onGrowlClosed(final Growl growl) {
		activeGrowls.remove(growl);
		completedGrowls.add(growl);
		growl.setDialog(null);
		repositionGrowls();
	}

	/**
	 * Repositions all of the growls.
	 */
	private void repositionGrowls() {
		for (int growlIndex = 0; growlIndex < activeGrowls.size(); growlIndex++) {
			Growl growl = activeGrowls.get(growlIndex);
			if (growl.getGrowlIndex() != growlIndex) {
				moveGrowl(growl, growl.getGrowlIndex(), growlIndex);
			}
		}
	}

	/**
	 * Moves a growl from one position to another in the stack of growls.
	 * @param growl
	 * @param fromGrowlIndex
	 * @param toGrowlIndex
	 */
	private void moveGrowl(Growl growl, int fromGrowlIndex, int toGrowlIndex) {
		int fromBottom = -1;
		try {
			fromBottom = new Integer(growl.getDialog().getElement().getStyle().getBottom().split("px")[0]).intValue();
		} catch (Throwable t) {
			fromBottom = ((GrowlConstants.GROWL_HEIGHT + GrowlConstants.GROWL_MARGIN) * fromGrowlIndex) + GrowlConstants.GROWL_MARGIN;
		}
		int toBottom = ((GrowlConstants.GROWL_HEIGHT + GrowlConstants.GROWL_MARGIN) * toGrowlIndex) + GrowlConstants.GROWL_MARGIN;
		MoveAnimation animation = new MoveAnimation(growl.getDialog(), "bottom", fromBottom, toBottom);
		animation.run(200);
		growl.setGrowlIndex(toGrowlIndex);
	}

	/**
	 * Positions the growl dialog.
	 * @param growl
	 */
	private void positionAndShowGrowlDialog(Growl growl) {
		GrowlDialog dialog = growl.getDialog();
		int growlIndex = growl.getGrowlIndex();
		
		// Show the dialog first, but make it invisible (so GWT can do its absolute positioning mojo)
		dialog.getElement().getStyle().setVisibility(Visibility.HIDDEN);
	    dialog.show();

	    // Calculate the growl dialog's position (based on its size and growl index)
	    int bottom = ((GrowlConstants.GROWL_HEIGHT + GrowlConstants.GROWL_MARGIN) * growlIndex) + GrowlConstants.GROWL_MARGIN;
		int right = GrowlConstants.GROWL_MARGIN;

	    // Now pin the growl to the right using fixed positioning
		dialog.getElement().getStyle().setPosition(Position.FIXED);
		dialog.getElement().getStyle().setBottom(bottom, Unit.PX);
		dialog.getElement().getStyle().setRight(right, Unit.PX);
		dialog.getElement().getStyle().setProperty("left", null);
		dialog.getElement().getStyle().setProperty("top", null);
	    dialog.getElement().getStyle().setVisibility(Visibility.VISIBLE);
	}

	/**
	 * Creates a new growl.
	 * @param title
	 * @param message
	 */
	private Growl createGrowl(String title, String message) {
		Growl growl = new Growl(growlCounter++, title, message);
		int growlIndex = this.activeGrowls.size();
		growl.setGrowlIndex(growlIndex);
		this.activeGrowls.add(growl);
		return growl;
	}

	/**
	 * Creates the dialog for the growl (notification window).
	 * @param growl
	 * @param title
	 * @param message
	 */
	private void createGrowlDialog(final Growl growl, String title, String message) {
		final GrowlDialog dialog = new GrowlDialog(title, message) {
			@Override
			protected void onMouseIn() {
				super.onMouseIn();
				growl.getAliveTimer().cancel();
				growl.getAutoCloseAnimation().cancel();
			}
			@Override
			protected void onMouseOut() {
				super.onMouseOut();
				growl.getAliveTimer().schedule(5000);
			}
		};
		growl.setDialog(dialog);
		// Close handler (user closed the notification)
		dialog.addCloseHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				growl.getAliveTimer().cancel();
				onGrowlClosed(growl);
			}
		});
	}

	/**
	 * Creates any timers needed to control the growl.
	 * @param growl
	 */
	private void createGrowlTimers(final Growl growl) {
		// Create the timer that will control when the growl automatically goes away.
		Timer aliveTimer = new Timer() {
			@Override
			public void run() {
				growl.getAutoCloseAnimation().run(1000);
			}
		};
		growl.setAliveTimer(aliveTimer);
	}

	/**
	 * Creates any animations needed by the growl.
	 * @param growl
	 */
	private void createGrowlAnimations(final Growl growl) {
		// Create the animation used to make the growl go away (when the time comes)
		FadeOutAnimation fadeOut = new FadeOutAnimation(growl.getDialog()) {
			@Override
			protected void doOnComplete() {
				growl.getDialog().hide();
				onGrowlClosed(growl);
			}
		};
		growl.setAutoCloseAnimation(fadeOut);
	}

}
