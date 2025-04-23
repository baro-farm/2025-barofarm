$(document).ready(function () {
    const contextPath = '${contextPath}';
    const productNum = $('#productNum').val(); // hidden input에서 productNum 가져오기

    // 페이지 로드 시 리뷰 첫 페이지 바로 로드
    loadReviews(1);

    // 리뷰 불러오기 (Ajax)
    function loadReviews(page) {
        $.ajax({
            url: contextPath + '/getProductReviews',
            method: 'GET',
            data: {
                productNum: productNum,
                page: page
            },
            dataType: 'json',
            success: function (data) {
                renderReviewTable(data.reviews);
                renderReviewPaging(data.totalPages, page);
            },
            error: function (xhr, status, error) {
                console.error('리뷰 불러오기 실패:', error);
            }
        });
    }

    // 리뷰 테이블 렌더링
    function renderReviewTable(reviews) {
        const $tbody = $('.reviewTable tbody');
        $tbody.empty(); // 기존 리뷰 지우기

        if (reviews.length === 0) {
            $tbody.append('<tr><td colspan="2">작성된 리뷰가 없습니다.</td></tr>');
            return;
        }

        $.each(reviews, function (index, review) {
            const row = `
                <tr>
                    <td class="reviewContent">
                        <div class="reviewId">
                            <strong>${review.userId}</strong>
                            <span class="stars">${review.stars}</span>
                            <span class="reviewDate">${review.date}</span>
                        </div>
                        <div class="prodName">${review.productName}</div>
                        <div class="reviewText">${review.content}</div>
                    </td>
                </tr>
            `;
            $tbody.append(row);
        });
    }
});
