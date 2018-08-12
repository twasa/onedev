package io.onedev.server.entityquery.pullrequest;

import java.util.Objects;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import io.onedev.server.entityquery.QueryBuildContext;
import io.onedev.server.model.Project;
import io.onedev.server.model.PullRequest;
import io.onedev.server.model.User;
import io.onedev.server.model.support.pullrequest.PullRequestConstants;
import io.onedev.server.entityquery.pullrequest.PullRequestQueryLexer;

public class SubmittedByCriteria extends PullRequestCriteria {

	private static final long serialVersionUID = 1L;

	private final User value;
	
	public SubmittedByCriteria(User value) {
		this.value = value;
	}

	@Override
	public Predicate getPredicate(Project project, QueryBuildContext<PullRequest> context) {
		Path<User> attribute = context.getRoot().get(PullRequestConstants.ATTR_SUBMITTER);
		return context.getBuilder().equal(attribute, value);
	}

	@Override
	public boolean matches(PullRequest request) {
		return Objects.equals(request.getSubmitter(), value);
	}

	@Override
	public boolean needsLogin() {
		return false;
	}

	@Override
	public String toString() {
		return PullRequestQuery.getRuleName(PullRequestQueryLexer.SubmittedBy) + " " + PullRequestQuery.quote(value.getName());
	}

}